package com.zhulong.business.system.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：行政区域 - 列表显示数据
 * @author 初。
 * @date 2019-03-28 09:23
 */
@ApiModel("行政区域 - 列表显示数据")
@Data
public class AreaSettingListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String guid;

    /**
     * 上级主键
     */
    @ApiModelProperty("上级主键")
    private String parentGuid;

    /**
     * 上级区域名
     */
    @ApiModelProperty("上级区域名")
    private String parentName;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private String code;

    /**
     * 全称-中文
     */
    @ApiModelProperty("全称-中文")
    private String fullNameZh;

    /**
     * 排序号
     */
    @ApiModelProperty("SORT_NUM")
    private BigDecimal sortNum;

    /**
     * 状态（0：无效，1：有效）
     */
    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

}