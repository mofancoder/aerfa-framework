package com.zhulong.business.system.busservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 描述：数据字典值保存DTO
 * <p>
 * Author: 初。
 * Date: 2019-3-20
 * Time: 15:30
 */
@ApiModel("数据字典值 - 数据字典值保存DTO")
@Data
public class DataDictionaryValueSaveDTO {

    @ApiModelProperty("数据字典主键")
    private String dictionaryGuid;

    @NotNull
    @Size(min = 1, max = 100)
    @ApiModelProperty("数据字典值编号")
    private String code;

    @ApiModelProperty("值-简称-中文")
    private String simpleValueZh;

    @ApiModelProperty("值-简称-英文")
    private String simpleValueEn;

    @ApiModelProperty("值-全程-中文")
    private String fullValueZh;

    @ApiModelProperty("值-全称-英文")
    private String fullValueEn;

    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

    @NotNull
    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    @ApiModelProperty("备注")
    private String remark;

}
