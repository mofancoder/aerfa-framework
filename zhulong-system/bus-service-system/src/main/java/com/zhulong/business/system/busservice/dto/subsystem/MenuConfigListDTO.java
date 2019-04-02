package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜单管理-列表对象
 * Created by shanb on 2019-2-27.
 */
@ApiModel("菜单管理-列表对象")
@Getter
@Setter
public class MenuConfigListDTO implements Serializable {

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("子系统主键")
    private String subSystemGuid;

    @ApiModelProperty("子系统名称")
    private String subSystemName;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("适用模式")
    private Short busModel;

    @ApiModelProperty("展示位置")
    private Short showPosition;

    @ApiModelProperty("级别")
    private Short level;

    @ApiModelProperty("排序")
    private BigDecimal sortNum;

    @ApiModelProperty("状态")
    private Short status;
}