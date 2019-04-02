package com.zhulong.business.system.baseservice.dto.subsystem;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 子系统管理DTO对象
 * Created by shanb on 2019-2-26.
 */
@Getter
@Setter
public class SubSystemDTO extends BaseDTO implements Serializable {

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "子系统分类主键")
    private String categoryGuid;

    @ApiModelProperty(value = "展示名称-中文")
    private String displayNameZh;

    @ApiModelProperty(value = "展示名称-英文")
    private String displayNameEn;

    @ApiModelProperty(value = "提示名称-中文")
    private String tipNameZh;

    @ApiModelProperty(value = "提示名称-英文")
    private String tipNameEn;

    @ApiModelProperty(value = "排序")
    private BigDecimal sortNum;

    @ApiModelProperty(value = "子系统类型")
    private String systemType;

    @ApiModelProperty(value = "业务模式")
    private Short busModel;

    @ApiModelProperty(value = "默认业务模式")
    private Short defaultBusModel;

    //多个用户类型，以逗号分隔进行传输
    @ApiModelProperty(value = "用户类型")
    private String userType;

    @ApiModelProperty(value = "状态")
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