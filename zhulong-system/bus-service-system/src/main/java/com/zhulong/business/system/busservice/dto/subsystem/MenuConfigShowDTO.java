package com.zhulong.business.system.busservice.dto.subsystem;

import com.zhulong.business.system.baseservice.dto.subsystem.MenuConfigDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 展示数据
 * Created by shanb on 2019-2-27.
 */
@ApiModel("展示对象")
@Getter
@Setter
public class MenuConfigShowDTO extends MenuConfigDTO implements Serializable {

    @ApiModelProperty("子系统名称")
    private String subSystemName;
}