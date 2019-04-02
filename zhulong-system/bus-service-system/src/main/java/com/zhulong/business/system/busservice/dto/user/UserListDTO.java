package com.zhulong.business.system.busservice.dto.user;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户列表展示，显示的DTO对象
 * Created by shanb on 2019-1-15.
 */
@Getter
@Setter
public class UserListDTO {

    private String guid;

    private String userName;

    private String account;

    private String deptGuid;

    private String deptName;

    private String orgGuid;

    private String orgName;

    private Boolean validate;

    private Integer sortNum;
}