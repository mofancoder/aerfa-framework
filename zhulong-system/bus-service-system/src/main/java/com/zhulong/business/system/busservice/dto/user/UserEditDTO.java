package com.zhulong.business.system.busservice.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by shanb on 2019-1-15.
 * 用户编辑时，传入接口的DTO对象
 */
@Getter
@Setter
public class UserEditDTO {

    @NotNull
    @Size(min = 1,max = 50)
    private String userName;

    @NotNull
    @Size(min = 1,max = 40)
    private String guid;

//    private String account;

    @NotNull
    private Short idType;

    @Size(min = 1,max = 20)
    private String idNum;

    @Size(min = 8,max = 11)
    private String phoneNum;

    @Size(min = 8,max = 20)
    private String telNum;

    @Size(min = 1,max = 20)
    private String email;

    @NotNull
    private Boolean validate;

    @NotNull
    private Integer sortNum;

    private String description;

}