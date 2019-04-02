package com.zhulong.business.system.busservice.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 用户修改状态，传入的DTO对象
 * Created by shanb on 2019-1-15.
 */
@Getter
@Setter
public class UserCgStatusDTO {

    @NotNull
    private String guid;

    @NotNull
    private Boolean validate;

    @NotNull
    @Size(min = 1,max = 2000)
    private String changeStatusReason;
}