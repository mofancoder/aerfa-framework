package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 列表数据显示
 * Created by shanb on 2019-3-25.
 */
@ApiModel("菜单项列表显示数据")
@Getter
@Setter
public class MenuItemListDTO implements Serializable {

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("完整页面地址")
    private String fullPageUrl;

    @ApiModelProperty("上级菜单项主键")
    private String parentGuid;

    @ApiModelProperty("上级菜单项名称")
    private String parentName;

    @ApiModelProperty("是否展示")
    private Boolean dispaly;

    @ApiModelProperty("状态（0：禁用，1：启用）")
    private Integer status;

    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

}