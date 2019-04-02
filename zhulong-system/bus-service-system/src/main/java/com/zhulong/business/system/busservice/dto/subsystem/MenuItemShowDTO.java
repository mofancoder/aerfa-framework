package com.zhulong.business.system.busservice.dto.subsystem;

import com.zhulong.business.system.baseservice.dto.subsystem.MenuItemDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by shanb on 2019-3-25.
 */
@ApiModel("菜单项-查看页面显示数据")
@Getter
@Setter
public class MenuItemShowDTO extends MenuItemDTO {

    @ApiModelProperty("子系统主键")
    private String subSystemGuid;

    @ApiModelProperty("子系统名称")
    private String subSystemName;

    @ApiModelProperty("菜单项名称")
    private String menuConfigName;

    @ApiModelProperty("上级菜单项名称")
    private String parentName;

}