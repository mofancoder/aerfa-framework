package com.zhulong.framework.auth.credence;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * 登录凭证
 * Created by shanb on 2019-1-10.
 */
@Getter
@Setter
public class LoginCredence implements Serializable {

    /**
     * 登录类型，1：CA登录  2：用户名密码登录
     */
    private Integer loginType;

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

    /**
     * 证书信息，通过后台解析成证书，进行数据库信息的对比。
     */
    private String cert;

    /**
     * 签名串
     */
    private String signStr;

    public PasswordCredence extractPasswordCredence(){
        if(loginType!=null && loginType==2){
            PasswordCredence credence = new PasswordCredence();
            BeanUtils.copyProperties(this,credence);
            return credence;
        }
        return null;
    }

    public CertCredence extractCaCredence(){
        if(loginType!=null && loginType==1){
            CertCredence credence = new CertCredence();
            BeanUtils.copyProperties(this,credence);
            return credence;
        }
        return null;
    }
}