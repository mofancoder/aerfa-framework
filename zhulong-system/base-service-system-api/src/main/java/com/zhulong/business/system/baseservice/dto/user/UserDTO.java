package com.zhulong.business.system.baseservice.dto.user;

import com.zhulong.framework.common.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * Created by shanb on 2019-1-15.
 * 用户对象的DTO,基础服务层可以提供较为通用DTO，以实现增删改查功能。
 */
@Getter
@Setter
public class UserDTO extends BaseDTO implements Serializable {

    private String guid;

    private String userName;

    private String account;

    private String password;

    private String deptGuid;

    private String deptName;

    private Short idType;

    private String idNum;

    private String phoneNum;

    private String telNum;

    private String email;

    private Boolean validate;

    private String changeStatusReason;

    private Boolean locked;

    private Long lockedTime;

    private Integer sortNum;

    private String description;

}