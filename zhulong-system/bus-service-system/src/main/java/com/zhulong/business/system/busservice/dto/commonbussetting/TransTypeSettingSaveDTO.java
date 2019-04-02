package com.zhulong.business.system.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：交易类型 - 保存数据
 * @author 初。
 * @date 2019-03-28 09:17
 */
@ApiModel("交易类型 - 保存数据")
@Data
public class TransTypeSettingSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上级主键
     */
    @ApiModelProperty("上级主键")
    private String parentGuid;

    /**
     * 编号
     */
    @NotNull
    @Size(min = 1, max = 100)
    @ApiModelProperty("编号")
    private String code;

    /**
     * 简称-中文
     */
    @ApiModelProperty("简称-中文")
    private String simpleNameZh;

    /**
     * 简称-英文
     */
    @ApiModelProperty("简称-英文")
    private String simpleNameEn;

    /**
     * 全称-中文
     */
    @ApiModelProperty("全称-中文")
    private String fullNameZh;

    /**
     * 全称-英文
     */
    @ApiModelProperty("全称-英文")
    private String fullNameEn;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

    /**
     * 状态（0：无效，1：有效）
     */
    @NotNull
    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}