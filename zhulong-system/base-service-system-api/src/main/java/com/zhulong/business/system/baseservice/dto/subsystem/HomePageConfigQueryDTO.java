package com.zhulong.business.system.baseservice.dto.subsystem;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by shanb on 2019-2-26.
 */
@ApiModel("首先管理-查询参数")
@Getter
@Setter
public class HomePageConfigQueryDTO  implements Serializable {

    @ApiModelProperty("子系统主键")
    private String subSystemGuid;

    @ApiModelProperty("子系统名称，匹配查询")
    private String name;

    @ApiModelProperty("子系统编号，匹配查询")
    private String code;

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;
}