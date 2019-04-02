package com.zhulong.framework.auth.common;

import com.zhulong.framework.common.inteface.BaseUser;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shanb on 2019-1-11.
 */
@Getter
@Setter
public class AuthUser implements BaseUser,Serializable {

    //用户类型
    private String userType;

    private String guid;

    private String name;

    private String idNum;

    //机构信息，当用户类型为机构或者机构下的用户时，此值有效
    private AuthOrganizationInfo organizationInfo;

    //法人信息，当用户类型为法人或者法人下人员的时候，此值有效
    private AuthLegalPersonInfo legalPersonInfo;

    @Override
    public String getBh() {
        return this.idNum;
    }
}