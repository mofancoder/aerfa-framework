package com.zhulong.framework.auth.credence;

import com.zhulong.framework.auth.common.AuthUser;
import lombok.Getter;
import lombok.Setter;

/**
 * 账户认证的信息
 * Created by shanb on 2019-2-12.
 */
@Getter
@Setter
public class AccountAuthInfo {

    private AuthUser authUser;

    /**
     * 需要匹配的信息，账号密码登录保存的为密码
     */
    private Object needMatchInfo;

    /**
     * 账号主键
     */
    private String accountGuid;

    /**
     * 是否被锁定
     * 0:无效 1：有效 2：锁定
     */
    private Short status;

    /**
     * 被锁定时间
     */
    private Long lockedTime;

    /**
     * 登录失败次数
     */
    private Integer loginFuilreCount;

    /**
     * 是否认证通过
     */
    private Boolean authed = false;

}