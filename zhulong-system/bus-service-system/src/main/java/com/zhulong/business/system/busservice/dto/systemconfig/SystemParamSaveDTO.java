package com.zhulong.business.system.busservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 描述：系统参数保存DTO
 *
 * @author 初。
 * @date 2019-03-20 10:01:09
 */
@ApiModel("系统参数 - 保存DTO")
@Data
public class SystemParamSaveDTO {

    @ApiModelProperty("类型主键")
    private String typeGuid;

    @NotNull
    @Size(min = 1, max = 100)
    @ApiModelProperty("参数编号")
    private String code;

    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty("参数名称")
    private String name;

    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty("参数值")
    private String paramValue;

    @NotNull
    @ApiModelProperty("级别（1：平台级，2：机构级）")
    private Short suitLevel;

    @ApiModelProperty("机构主键-机构级别的，记录此主键")
    private String organizationGuid;

    @NotNull
    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    @ApiModelProperty("备注")
    private String remark;

}
