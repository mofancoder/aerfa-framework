package com.zhulong.framework.common.enums;

import java.util.Arrays;
import java.util.List;

/**
 * 人员类型Code的枚举
 * Created by shanb on 2019-3-27.
 */
public enum  UserTypeCode implements BaseEnum<String> {

    MANAGE_ORGANIZATION("manageOrganization","运营机构"),
    MANAGE_ORGANIZATION_PERSON("manageOrganizationPerson","运营机构人员"),
    TRADE_ORGANIZATION("trade_organization","交易机构"),
    TRADE_ORGANIZATION_PERSON("tradeOrganizationPerson","交易机构人员"),
    LEGAL_PERSON("legalPerson","法人"),
    LEGAL_PERSON_PERSON("legalPersonPerson","法人下的用户"),
    NATURAL_PERSON("natural_person","自然人"),
    EXPERT("expert","专家");

    UserTypeCode(String code,String description){
        this.code = code;
        this.description = description;
    }

    private String code;

    private String description;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public static List<String> getOrganizationCodeList(){
        return Arrays.asList(MANAGE_ORGANIZATION.getCode(),TRADE_ORGANIZATION.getCode());
    }
}