package com.zhulong.business.system.busservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 描述：系统参数分类保存DTO
 *
 * @author 初。
 * @date 2019-03-20 10:01:25
 */
@ApiModel("系统参数分类 - 保存DTO")
@Data
public class SystemParamTypeSaveDTO {

    @NotNull
    @Size(min = 1, max = 100)
    @ApiModelProperty("系统参数分类编号")
    private String code;

    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty("系统参数分类名称")
    private String name;

    @ApiModelProperty("系统参数分类排序号")
    private BigDecimal sortNum;

    @NotNull
    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    @ApiModelProperty("备注")
    private String remark;

}
