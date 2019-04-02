package com.zhulong.business.system.baseservice.dto.systemconfig;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述：工作时间管理DTO
 *
 * @author 初。
 * @date 2019-03-21 04:05:52
 */
@ApiModel("工作时间管理-数据传输对象")
@Data
public class WorkingTimeConfigDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    @ApiModelProperty("适用月份，各月份使用英文逗号隔开")
    private String months;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("星期一工作时间json数组字符串")
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
