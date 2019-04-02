package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 首页管理-列表展示对象
 * Created by shanb on 2019-2-27.
 */
@ApiModel("首页管理-列表展示对象")
@Getter
@Setter
public class HomePageConfigListDTO {

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("所属子系统")
    private String subSystemGuid;

    @ApiModelProperty("所属子系统名称")
    private String subSystemName;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("适用业务模式")
    private Short busModel;

    @ApiModelProperty("状态")
    private Short status;
}