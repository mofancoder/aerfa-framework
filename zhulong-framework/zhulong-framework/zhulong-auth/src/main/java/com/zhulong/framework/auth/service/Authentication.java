package com.zhulong.framework.auth.service;

import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.credence.AccountAuthInfo;
import com.zhulong.framework.auth.credence.CertCredence;
import com.zhulong.framework.auth.credence.LoginCredence;
import com.zhulong.framework.auth.credence.PasswordCredence;

import java.util.Set;

/**
 * 认证接口，基于不同的用户认证，都实现此接口。
 * Created by shanb on 2019-1-11.
 */
public interface Authentication {

    /**
     * 认证用户名密码，需设置AuthUser字段和srcPassword
     */
    AccountAuthInfo authPasswordCredence(PasswordCredence passwordCredence);

    /**
     * 认证CA凭证
     */
    AccountAuthInfo authCaCredence(CertCredence certCredence);

    /**
     * 登录成功处理，更新账户信息  如是否锁定，最大登录次数等
     */
    void loginSuccess(AccountAuthInfo accountAuthInfo);

    /**
     * 登录失败处理，更新账户信息  如是否锁定
     */
    void loginFailure(AccountAuthInfo accountAuthInfo,boolean lockedAccount);

    /**
     *  认证成功后，根据用户信息获取授权的地址
     */
    Set<String> getAuthrazUrls(AuthUser user);

}