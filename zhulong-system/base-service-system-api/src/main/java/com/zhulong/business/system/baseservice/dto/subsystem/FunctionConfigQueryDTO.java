package com.zhulong.business.system.baseservice.dto.subsystem;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* 描述：功能管理数据查询对象
* @author shanb
* @date 2019-03-26 11:38
*/
@ApiModel("功能管理查询数据对象")
@Getter
@Setter
public class FunctionConfigQueryDTO implements Serializable {


    /**
    * 编码
    */
    @ApiModelProperty("编码")
    private String code;

    /**
    * 名称
    */
    @ApiModelProperty("名称")
    private String name;

    /**
    * 菜单项
    */
    @ApiModelProperty("菜单项")
    private String menuItemGuid;

    /**
    * 菜单主键-做冗余
    */
    @ApiModelProperty("菜单主键-做冗余")
    private String menuConfigGuid;

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;
}