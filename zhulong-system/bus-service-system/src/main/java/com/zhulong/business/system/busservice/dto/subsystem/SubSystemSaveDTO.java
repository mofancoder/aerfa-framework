package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * Created by shanb on 2019-2-26.
 */
@Getter
@Setter
@ApiModel("子系统功-保存对象")
public class SubSystemSaveDTO {

    @ApiModelProperty(value = "名称",allowableValues = "1-200字符")
    @NotNull
    @Size(min = 1,max = 200)
    private String name;

    @NotNull
    @ApiModelProperty(value = "子系统分类主键")
    private String categoryGuid;

    @NotNull
    @ApiModelProperty(value = "展示名称-中文")
    @Size(min = 1,max = 200)
    private String displayNameZh;

    @ApiModelProperty(value = "展示名称-英文",allowableValues = "200字符",allowEmptyValue = true)
    @Size(max = 200)
    private String displayNameEn;

    @ApiModelProperty(value = "提示名称-中文",allowableValues = "200字符",allowEmptyValue = true)
    @Size(max = 200)
    private String tipNameZh;

    @ApiModelProperty(value = "提示名称-英文",allowableValues = "200字符",allowEmptyValue = true)
    @Size(max = 200)
    private String tipNameEn;

    @ApiModelProperty(value = "排序",allowableValues = "0-9999")
    @Max(9999)
    private BigDecimal sortNum;

    @ApiModelProperty(value = "子系统类型")
    @NotNull
    private String systemType;

    @ApiModelProperty(value = "业务模式")
    @NotNull
    private Short busModel;

    @ApiModelProperty(value = "默认业务模式")
    @NotNull
    private Short defaultBusModel;

    //多个用户类型，以逗号分隔进行传输
    @ApiModelProperty(value = "用户类型")
    @NotNull
    private String userType;

    @ApiModelProperty(value = "状态")
    @NotNull
    private Short status;

    @ApiModelProperty(value = "小图标")
    private String smallIconGuid;

    @ApiModelProperty(value = "大图标")
    private String bigIconGuid;

    @ApiModelProperty(value = "背景图片")
    private String backGroudPicGuid;

    @ApiModelProperty(value = "描述-中文")
    private String descZh;

    @ApiModelProperty(value = "描述-英文")
    private String descEn;
}