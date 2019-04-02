package com.zhulong.business.system.busservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 描述：数据字典类型保存DTO
 *
 * @author 初。
 * @date 2019-03-20 10:00:06
 */
@ApiModel("数据字典类型 - 数据字典类型保存DTO")
@Data
public class DataDictionaryTypeSaveDTO {

    @NotNull
    @Size(min = 1, max = 100)
    @ApiModelProperty("分类编号")
    private String code;

    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

    @NotNull
    @ApiModelProperty(value = "状态", allowableValues = "0:无效,1:有效）")
    private Short status;

    @ApiModelProperty("备注")
    private String remark;

}
