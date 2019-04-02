package com.zhulong.framework.auth.credence;

import com.zhulong.framework.auth.common.AuthUser;
import lombok.Getter;
import lombok.Setter;

/**
 * CA登录的凭证信息
 * Created by shanb on 2019-2-12.
 */
@Getter
@Setter
public class CertCredence {
    /**
     * 登录的key,用于查询验证码和签名原文信息
     */
    private String loginKey;

    /**
     * 人员类型
     */
    private String userType;

    /**
     * 证书信息，通过后台解析成证书，进行数据库信息的对比。
     */
    private String cert;

    /**
     * 签名串
     */
    private String signStr;

}