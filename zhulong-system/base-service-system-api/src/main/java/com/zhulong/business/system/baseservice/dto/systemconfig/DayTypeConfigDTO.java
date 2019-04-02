package com.zhulong.business.system.baseservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述：日期类型配置DTO
 *
 * @author 初。
 * @date 2019-03-20 10:00:44
 */
@ApiModel("日期类型配置 - 数据传输对象")
@Data
public class DayTypeConfigDTO {

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("年份")
    private Short year;

    @ApiModelProperty("月")
    private Short month;

    @ApiModelProperty("日")
    private Short day;

    @ApiModelProperty("日子类型(0:放假，1：上班)")
    private Short dayType;

    @ApiModelProperty("创建时间")
    private Long createTime;

}
