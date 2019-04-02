package com.zhulong.framework.auth.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by shanb on 2019-3-27.
 * 登录用户所属的法人信息
 */
@Getter
@Setter
public class AuthLegalPersonInfo implements Serializable {

    private String guid;

    private String unifiedCode;

    private String name;
}