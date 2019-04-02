package com.zhulong.business.system.baseservice.dto.subsystem;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜单管理-数据对象
 * Created by shanb on 2019-2-27.
 */
@ApiModel("菜单管理-数据传输对象")
@Getter
@Setter
public class MenuConfigDTO extends BaseDTO implements Serializable {

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("编号")
    private String code;

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