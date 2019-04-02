package com.zhulong.business.system.busservice.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 用户编辑时，传入接口的DTO对象
 * Created by shanb on 2019-1-15.
 */
@Getter
@Setter
@ApiModel("用户传输对象")
public class UserAddDTO {

    @NotNull
    @Size(min = 1,max = 50)
    @ApiModelProperty(value = "用户名称",dataType = "string")
    private String userName;

    @NotNull
    @Size(min = 8,max = 20)
    @ApiModelProperty(value = "账号",allowableValues = "8-20位")
    private String account;

    @NotNull
    @Size(min = 1,max = 40)
    @ApiModelProperty(value = "部门主键",allowableValues = "1-40位")
    private String deptGuid;

    @NotNull
    @Size(min = 1,max = 50)
    @ApiModelProperty(value = "部门名称",allowableValues = "1-50位")
    private String deptName;

    @NotNull
    @ApiModelProperty(value = "证件类型",allowableValues = "1:身份证,2：..")
    private Short idType;

    @NotNull
    @Size(min = 1,max = 20)
    @ApiModelProperty(value = "证件号码",allowableValues = "1-20位")
    private String idNum;

    @Size(min = 8,max = 11)
    @ApiModelProperty(value = "手机号码",allowableValues = "8-11位",allowEmptyValue = true)
    private String phoneNum;

    @Size(min = 8,max = 20)
    @ApiModelProperty(value = "电话号码",allowableValues = "8-20位",allowEmptyValue = true)
    private String telNum;

    @Size(min = 1,max = 20)
    @ApiModelProperty(value = "Email",allowableValues = "1-20位",allowEmptyValue = true)
    private String email;

    @NotNull
    @ApiModelProperty(value = "是否可用")
    private Boolean validate;

    @NotNull
    @ApiModelProperty(value = "排序号码")
    private Integer sortNum;

    @ApiModelProperty(value = "排序号码",allowEmptyValue = true)
    private String description;


}