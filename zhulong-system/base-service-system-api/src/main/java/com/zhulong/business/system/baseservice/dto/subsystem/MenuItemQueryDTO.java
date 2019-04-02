package com.zhulong.business.system.baseservice.dto.subsystem;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 菜单项查询DTO
 * Created by shanb on 2019-3-25.
 */
@ApiModel("菜单项查询数据对象")
@Getter
@Setter
public class MenuItemQueryDTO  implements Serializable {

    @ApiModelProperty("上级菜单")
    private String parentGuid;

    @ApiModelProperty("菜单主键")
    private String menuConfigGuid;

    @ApiModelProperty("编号-匹配查询")
    private String code;

    @ApiModelProperty("名称-匹配查询")
    private String name;

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;
}