package com.zhulong.business.system.busservice.dto.subsystem;

import com.zhulong.business.system.baseservice.dto.subsystem.HomePageConfigDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 首页配置，展示DTO对象
 * Created by shanb on 2019-2-27.
 */
@Getter
@Setter
public class HomePageConfigShowDTO extends HomePageConfigDTO {

    @ApiModelProperty("子系统名称")
    private String subSystemName;

    @ApiModelProperty("子系统编号")
    private String subSystemCode;
}