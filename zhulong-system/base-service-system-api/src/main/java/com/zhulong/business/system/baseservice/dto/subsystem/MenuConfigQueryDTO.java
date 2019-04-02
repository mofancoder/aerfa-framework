package com.zhulong.business.system.baseservice.dto.subsystem;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 菜单管理-数据查询对象
 * Created by shanb on 2019-2-27.
 */
@ApiModel("数据查询对象")
@Getter
@Setter
public class MenuConfigQueryDTO implements Serializable {

    @ApiModelProperty("子系统主键")
    private String subSystemGuid;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;
}