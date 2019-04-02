package com.zhulong.framework.auth.credence;

import com.zhulong.framework.auth.common.AuthUser;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by shanb on 2019-2-12.
 * 用户名密码登录的凭证信息
 */
@Getter
@Setter
public class PasswordCredence {

    /**
     * 登录的key,用于查询验证码和签名原文信息
     */
    private String loginKey;

    /**
     * 人员类型
     */
    private String userType;

    /**
     * 用户名信息
     */
    private String accountName;

    /**
     * 加密后密码
     */
    private String password;

}