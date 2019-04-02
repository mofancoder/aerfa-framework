package com.zhulong.business.system.baseservice.dto.systemconfig;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：数据字典值DTO
 *
 * @author 初。
 * @date 2019-03-20 09:59:31
 */
@ApiModel("数据字典值-数据传输对象")
@Data
public class DataDictionaryValueDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("字典主键")
    private String dictionaryGuid;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("名称")
    private String name;

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

    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    @ApiModelProperty("备注")
    private String remark;

}
