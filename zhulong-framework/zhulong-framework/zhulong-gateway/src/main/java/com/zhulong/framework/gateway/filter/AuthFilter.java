package com.zhulong.framework.gateway.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhulong.framework.auth.common.AuthConstance;
import com.zhulong.framework.gateway.config.NotAuthConfigBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于对api进行权限认证。委托给认证服务进行token的判断；路径是否有权限等。
 * 需放在rewrite之前，path的第一层，表示系统的英文名称
 * Created by shanb on 2019-1-10.
 */
@Slf4j
public class AuthFilter implements GlobalFilter,Ordered {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NotAuthConfigBean notAuthConfigBean;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain chain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        ServerHttpResponse response = serverWebExchange.getResponse();
        //是否有设置sys_code，如果有则进行判断。否则  直接放行
            String path = request.getURI().getRawPath();
            boolean isNeedAuth = isNeedAuth(path);
            if(isNeedAuth){
                List<String> tokenList = request.getHeaders().get(AuthConstance.TOKEN_KEY);
                if(tokenList!=null && !tokenList.isEmpty()){
                    String token = tokenList.get(0);
                    int canAccessStatus = tokenCanAccess(token,path);
                    if(200==canAccessStatus){
                        Map<String,Object> authMap = getAuthUser(token);
                        ObjectMapper objectMapper = new ObjectMapper();
                        try {
                            String userInfo = objectMapper.writeValueAsString(authMap);
                            ServerHttpRequest request_n = request.mutate().headers((httpHeaders) -> {
                                try {
                                    String headUserInfo = Base64.getEncoder().encodeToString(userInfo.getBytes("UTF-8"));
                                    httpHeaders.add(AuthConstance.HEAD_USER_INFO,headUserInfo);
                                } catch (UnsupportedEncodingException e) {
                                    //do nothing
                                }
                            }).build();
                            return chain.filter(serverWebExchange.mutate().request(request_n).build());
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException("can not cast authUser to map");
                        }

                    }else{
                        response.setStatusCode(HttpStatus.resolve(canAccessStatus));

                    }
                }else{
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                }
            }else{
                return chain.filter(serverWebExchange);
            }

        DataBuffer bodyDataBuffer = response.bufferFactory().wrap("".getBytes());
        return response.writeWith(Mono.just(bodyDataBuffer));
    }

    /**
     * 从系统配置中获取配置进行判断，或者配置文件中
     */
    private boolean isNeedAuth(String url){
        List<String> notAuthUrls = notAuthConfigBean.getUrls();
        if(notAuthUrls!=null){
            AntPathMatcher antPathMatcher = new AntPathMatcher();
            for (String notAuthUrl : notAuthUrls) {
                if(antPathMatcher.match(notAuthUrl,url)){
                   return false;
                }
            }
        }
        return true;
    }

    /**
     * 在授权的接口中进行token的判断和权限的判断
     * 返回httpStatus状态表示
     * 200：ok
     * 401:token不合法或者过期，需要进行登录
     * 403:token权限不够访问特定的url地址
     */
    private int tokenCanAccess(String token,String url){
        Map<String,Object> postMap = new HashMap<>();
        postMap.put("token",token);
//        postMap.put("sysCode",sysPath);
        postMap.put("path",url);
        Integer result = restTemplate.postForObject("http://auth/auth/verifyTokenAndRefresh?token={token}&path={path}",postMap,Integer.class,postMap);
        return result;
    }

    private Map<String,Object> getAuthUser(String token){
        Map<String,Object> result = restTemplate.getForObject("http://auth/auth/getAuthUser?token={1}",HashMap.class,token);
        return result;
    }

    @Override
    public int getOrder() {
        return -1;
    }
}