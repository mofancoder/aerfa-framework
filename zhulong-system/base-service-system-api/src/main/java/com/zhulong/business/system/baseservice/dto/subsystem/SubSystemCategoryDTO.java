package com.zhulong.business.system.baseservice.dto.subsystem;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 子系统分类-数据传输对象
 * Created by shanb on 2019-2-25.
 */
@Getter
@Setter
@ApiModel("子系统分类-通用数据传输对象")
public class SubSystemCategoryDTO extends BaseDTO implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String guid;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 展示名称-中文
     */
    @ApiModelProperty("展示名称-中文")
    private String displayNameZh;

    /**
     * 展示名称-英文
     */
    @ApiModelProperty("展示名称-英文")
    private String displayNameEn;

    /**
     * 提示名称-中文
     */
    @ApiModelProperty("提示名称-中文")
    private String tipNameZh;

    /**
     * 提示名称-英文
     */
    @ApiModelProperty("提示名称-英文")
    private String tipNameEn;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

    /**
     * 是否有效
     */
    @ApiModelProperty("状态")
    private Short status;

    /**
     * 描述-中文
     */
    @ApiModelProperty("描述-中文")
    private String descZh;

    /**
     * 描述-英文
     */
    @ApiModelProperty("描述-英文")
    private String descEn;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;
}