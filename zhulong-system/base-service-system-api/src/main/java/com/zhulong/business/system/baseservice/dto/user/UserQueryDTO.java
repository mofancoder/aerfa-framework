package com.zhulong.business.system.baseservice.dto.user;

import com.zhulong.framework.common.dto.PageOrderDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 用户查询Bean
 * Created by shanb on 2019-1-16.
 */
@Getter
@Setter
public class UserQueryDTO implements Serializable {

    private String guid;

    private String userName;

    private String account;

    private String deptGuid;

    private Short idType;

    private String idNum;

    private String phoneNum;

    private String telNum;

    private String email;

    private Boolean validate;

    private String creatorGuid;

    private PageOrderDTO pageOrderDTO;

}