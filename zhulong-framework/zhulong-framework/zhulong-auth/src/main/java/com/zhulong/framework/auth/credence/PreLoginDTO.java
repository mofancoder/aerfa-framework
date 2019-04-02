package com.zhulong.framework.auth.credence;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by shanb on 2019-1-10.
 */
@Getter
@Setter
public class PreLoginDTO implements Serializable {

    //登录所需用到的key，在redis中存储，用于后台比较验证码或者原文信息
    private String loginKey;

    //验证码
    private String verificationCode;

    /**
     * 签名原文
     */
    private String originalStr;
}