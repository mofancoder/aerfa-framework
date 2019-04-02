package com.zhulong.business.system.baseservice.dto.subsystem;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by shanb on 2019-2-26.
 * 子系统查询DTO对象
 */
@Getter
@Setter
public class SubSystemQueryDTO implements Serializable {

    @ApiModelProperty("子系统主键")
    private String categoryGuid;

    @ApiModelProperty("子系统编号")
    private String code;

    @ApiModelProperty("子系统名称")
    private String name;

    @ApiModelProperty("分页信息")
    private PageOrderDTO pageOrderDTO;
}