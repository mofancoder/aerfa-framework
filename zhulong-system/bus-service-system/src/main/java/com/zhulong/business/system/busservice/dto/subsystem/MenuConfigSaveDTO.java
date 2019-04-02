package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜单管理-菜单新增信息
 * Created by shanb on 2019-2-27.
 */
@Getter
@Setter
@ApiModel("菜单配置")
public class MenuConfigSaveDTO implements Serializable {

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("子系统主键")
    private String subSystemGuid;

    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

    @ApiModelProperty("展示名称-中文")
    private String displayNameZh;

    @ApiModelProperty("展示名称-英文")
    private String displayNameEn;

    @ApiModelProperty("状态")
    private Short status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("适用模式")
    private Short busModel;

    @ApiModelProperty("展示位置")
    private Short showPosition;

    @ApiModelProperty("展示类型")
    private Short showType;

    @ApiModelProperty("是否展示序号")
    private Boolean showSortNum;

    @ApiModelProperty("级别")
    private Short level;

    @ApiModelProperty("类型")
    private Short type;

    @ApiModelProperty("业务流程")
    private String busFlowGuid;
}