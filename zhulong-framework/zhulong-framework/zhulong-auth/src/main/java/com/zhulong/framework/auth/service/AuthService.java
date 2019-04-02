package com.zhulong.framework.auth.service;

import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.credence.LoginCredence;
import com.zhulong.framework.auth.credence.PreLoginDTO;
import com.zhulong.framework.auth.dto.LoginRtDTO;
import com.zhulong.framework.common.dto.ResultDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 针对系统进行登录，鉴权等。
 * Created by shanb on 2019-1-10.
 */
@RequestMapping("auth")
public interface AuthService {

    /**
     * 登录前，服务端存入信息，返回给前端
     */
    @PostMapping("preLogin")
    PreLoginDTO preLogin();

    /**
     * 根据会话信息进行登录的判断,返回授权的token。
     */
    @PostMapping("login")
    ResultDTO<LoginRtDTO> login(@RequestBody LoginCredence credence);

    /**
     * 登出
     */
    @PostMapping("/logout")
    ResultDTO<Void> logout(String token);

    /**
     * 验证token，如果合理，刷新过期时间。
     */
    @PostMapping("verifyTokenAndRefresh")
    int verifyTokenAndRefresh(String token,String path);


    @GetMapping("/getAuthUser")
    AuthUser getAuthUserByToken(String token);
}