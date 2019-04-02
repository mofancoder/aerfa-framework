package com.zhulong.business.system.busservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 描述：数据字典保存DTO
 *
 * @author 初。
 * @date 2019-03-19 02:01:42
 */
@ApiModel("数据字典 - 数据字典保存DTO")
@Data
public class DataDictionarySaveDTO {

    @NotNull
    @Size(min = 1, max = 100)
    @ApiModelProperty("数据字典编号")
    private String code;

    @NotNull
    @Size(min = 36, max = 36)
    @ApiModelProperty("数据字典分类guid")
    private String typeGuid;

    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty("数据字典名称")
    private String name;

    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

    @ApiModelProperty(value = "级别", allowableValues = "1, 2")
    private Short suitLevel;

    @NotNull
    @ApiModelProperty(value = "状态", allowableValues = "0, 1")
    private Short status;

    @ApiModelProperty("备注")
    private String remark;

}
