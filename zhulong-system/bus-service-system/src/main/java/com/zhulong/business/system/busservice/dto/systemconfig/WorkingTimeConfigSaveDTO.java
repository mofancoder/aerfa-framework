package com.zhulong.business.system.busservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 描述：工作时间管理保存DTO
 *
 * @author 初。
 * @date 2019-03-21 04:05:52
 */
@ApiModel("工作时间管理 - 保存DTO")
@Data
public class WorkingTimeConfigSaveDTO {

    @NotNull
    @Size(min = 1, max = 100)
    @ApiModelProperty("编号")
    private String code;

    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty("名称")
    private String name;

    @NotNull
    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    @NotNull
    @ApiModelProperty("适用月份，各月份使用英文逗号隔开")
    private String months;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("星期一工作时间json数组字符串，示例：[{\"start\":\"08:00\", \"end\":\"12:00\"}, {\"start\":\"14:00\", \"end\":\"16:00\"}]")
    private String monday;

    @ApiModelProperty("星期二工作时间json数组字符串")
    private String tuesday;

    @ApiModelProperty("星期三工作时间json数组字符串")
    private String wednesday;

    @ApiModelProperty("星期四工作时间json数组字符串")
    private String thursday;

    @ApiModelProperty("星期五工作时间json数组字符串")
    private String friday;

    @ApiModelProperty("星期六工作时间json数组字符串")
    private String saturday;

    @ApiModelProperty("星期天工作时间json数组字符串")
    private String sunday;

}
