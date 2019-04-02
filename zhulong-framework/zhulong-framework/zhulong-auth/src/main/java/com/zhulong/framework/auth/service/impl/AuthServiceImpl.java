package com.zhulong.framework.auth.service.impl;

import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.credence.*;
import com.zhulong.framework.auth.dto.LoginRtDTO;
import com.zhulong.framework.auth.service.AuthService;
import com.zhulong.framework.auth.service.Authentication;
import com.zhulong.framework.common.dto.ResultDTO;
import com.zhulong.framework.common.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 进行身份验证的类。
 * Created by shanb on 2019-1-10.
 */
@RestController
public class AuthServiceImpl implements AuthService {

    private static final String AUTHTICATION_SUFFIX = "Authentication";

    private static final String AUTH_REDIS_NAMESPACE = "auth_token";

    /**
     * 相同用户最大登录次数
     */
    private static final Integer CON_LOGIN_COUNT = 1;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${login.token.expire.time:30}")
    private Long expireTime;

    @Value("${lock.expire.time:1800000}")
    private Long lockExpireTime;

    @Value("${max.faulure.count:5}")
    private Integer maxFailureCount;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 注入授权的实现类 key为实现类的beanId,id的名称需要与sysCode+Authentication
     */
    @Autowired
    private Map<String,Authentication> authticationMap;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public PreLoginDTO preLogin() {
        String key = applicationName+":preLogin:"+UUID.randomUUID().toString();
        PreLoginDTO preLoginDTO = new PreLoginDTO();
        preLoginDTO.setLoginKey(key);
        preLoginDTO.setOriginalStr(UUID.randomUUID().toString());
        preLoginDTO.setVerificationCode("1234");
        redisTemplate.opsForValue().set(key,preLoginDTO,expireTime, TimeUnit.MINUTES);
        return preLoginDTO;
    }

    @Override
    public ResultDTO<LoginRtDTO> login(@RequestBody LoginCredence credence) {
        String userType =credence.getUserType();
        //根据不同的sysCode进行认证工作
        if(userType==null){
            throw new IllegalArgumentException("userType can not be null");
        }
        if(credence.getLoginType()==null){
            throw new IllegalArgumentException("loginType can not be null");
        }

        Authentication authtication = authticationMap.get(userType+AUTHTICATION_SUFFIX);
        AccountAuthInfo accountAuthInfo = null;
        if(authtication!=null) {
            //账号密码登录逻辑
            if (credence.getLoginType() == 2) {
                PasswordCredence passwordCredence = credence.extractPasswordCredence();
                accountAuthInfo = authtication.authPasswordCredence(passwordCredence);
                if (accountAuthInfo != null && accountAuthInfo.getAuthUser() != null) {
                    //密码认证
                    if (!StringUtils.isEmpty(accountAuthInfo.getNeedMatchInfo()) && passwordEncoder.matches(passwordCredence.getPassword(), accountAuthInfo.getNeedMatchInfo().toString())) {
                        accountAuthInfo.setAuthed(true);
                    }
                }
            } else if (credence.getLoginType() == 1) { //CA登录逻辑
                //TODO:认证签名信息
                CertCredence certCredence = credence.extractCaCredence();
                accountAuthInfo = authtication.authCaCredence(certCredence);
                if (accountAuthInfo != null && accountAuthInfo.getAuthUser() != null) {
                    accountAuthInfo.setAuthed(true);
                }
            }

            if (accountAuthInfo != null) {
                if(Short.valueOf("0").equals(accountAuthInfo.getStatus())){
                    //账号无效
                    return ResultDTO.error("ACCOUNT.INVALIDATE");
                }
                //账号是否被锁定
                if (Short.valueOf("2").equals(accountAuthInfo.getStatus()) && ((System.currentTimeMillis() - accountAuthInfo.getLockedTime()) < lockExpireTime)) {
                    return ResultDTO.error("ACCOUNT.LOCKED");
                }
            }

            //登录成功
            if (accountAuthInfo != null && accountAuthInfo.getAuthed()) {
                authtication.loginSuccess(accountAuthInfo);
                AuthUser authUser = accountAuthInfo.getAuthUser();
                authUser.setUserType(userType);
                //生成token,存入redis中
                String token = UUID.randomUUID().toString();
                Set<String> authUrls = authtication.getAuthrazUrls(authUser);
                String redisKey = applicationName + ":" + AUTH_REDIS_NAMESPACE + ":" + token;
                Map<String, Object> redisMap = new HashMap<>();
                redisMap.put("user", authUser);
                redisMap.put("authUrls", authUrls);
                redisTemplate.opsForHash().putAll(redisKey, redisMap);
                redisTemplate.expire(redisKey, expireTime, TimeUnit.MINUTES);
                //如果需要控制每个账户登录次数，则需增加账户和token的映射进行判断
                if(CON_LOGIN_COUNT>0) {
                    String userKey = applicationName + ":" + AUTH_REDIS_NAMESPACE + ":" + authUser.getGuid();
                    if(redisTemplate.hasKey(userKey)) {
                        //判断是否已登录
                        long size = redisTemplate.opsForList().size(userKey);
                        if (size >= CON_LOGIN_COUNT) {
                            for (long i = 0; i < (size + 1 - CON_LOGIN_COUNT); i++) {
                                redisTemplate.opsForList().leftPop(userKey);
                            }
                        }
                    }
                    redisTemplate.opsForList().rightPush(userKey,token);
                }

                LoginRtDTO dto = new LoginRtDTO();
                dto.setToken(token);
                dto.setAuthUser(authUser);
                return ResultDTO.of(dto);
            } else {
                if (accountAuthInfo != null && accountAuthInfo.getAuthUser() != null) {
                    //判断是否需要锁定
                    boolean needLocked = false;
                    if (accountAuthInfo.getLoginFuilreCount() >= maxFailureCount) {
                        needLocked = true;
                    }
                    authtication.loginFailure(accountAuthInfo, needLocked);
                }
                return ResultDTO.error("LOGIN.ERROR");
            }
        }else{
            throw new SystemException("NO AUTH FOUND");
        }
    }

    @Override
    public ResultDTO<Void> logout(String token) {
        String redisKey = applicationName+":"+AUTH_REDIS_NAMESPACE+":"+token;
        Boolean exist = redisTemplate.hasKey(redisKey);
        if(exist) {
            if (CON_LOGIN_COUNT > 0) {
                AuthUser authUser = (AuthUser) redisTemplate.opsForHash().get(redisKey,"user");
                String userKey = applicationName + ":" + AUTH_REDIS_NAMESPACE + ":" + authUser.getGuid();
                if(redisTemplate.hasKey(userKey)) {
                    redisTemplate.delete(userKey);
                }
            }
            redisTemplate.delete(redisKey);
        }
        return ResultDTO.of(null);
    }

    @Override
    public int verifyTokenAndRefresh(String token,String path) {
        String redisKey = applicationName+":"+AUTH_REDIS_NAMESPACE+":"+token;
        Boolean exist = redisTemplate.hasKey(redisKey);
        if(exist){
            //刷新token过期时间
            redisTemplate.expire(redisKey,expireTime,TimeUnit.MINUTES);
            if (CON_LOGIN_COUNT > 0) {
                AuthUser authUser = (AuthUser) redisTemplate.opsForHash().get(redisKey,"user");
                String userKey = applicationName + ":" + AUTH_REDIS_NAMESPACE + ":" + authUser.getGuid();
                if(redisTemplate.hasKey(userKey)) {
                    redisTemplate.delete(userKey);
                }
            }
            //判断是否可授权
            Set<String> authUrls = (Set<String>)redisTemplate.opsForHash().get(redisKey,"authUrls");
            AntPathMatcher matcher = new AntPathMatcher();
            boolean match = false;
            for (String authUrl : authUrls) {
                if(matcher.match(authUrl,path)){
                    match = true;
                    break;
                }
            }
            if(match){
                return HttpStatus.OK.value();
            }else{
                return HttpStatus.FORBIDDEN.value();
            }
        }else{
            return HttpStatus.UNAUTHORIZED.value();
        }
    }

    @Override
    public AuthUser getAuthUserByToken(String token) {
        String redisKey = applicationName+":"+AUTH_REDIS_NAMESPACE+":"+token;
        Boolean exist = redisTemplate.hasKey(redisKey);
        if(exist) {
            AuthUser authUser = (AuthUser) redisTemplate.opsForHash().get(redisKey, "user");
            return authUser;
        }
        return null;
    }
}