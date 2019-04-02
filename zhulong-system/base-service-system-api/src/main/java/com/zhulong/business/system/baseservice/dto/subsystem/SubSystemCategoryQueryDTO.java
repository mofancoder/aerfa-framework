package com.zhulong.business.system.baseservice.dto.subsystem;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 子系统分类数据查询对象
 * Created by shanb on 2019-2-25.
 */
@Getter
@Setter
@ApiModel("子系统分类-查询数据传输对象")
public class SubSystemCategoryQueryDTO implements Serializable {

    @ApiModelProperty("编号，匹配查询")
    private String code;

    @ApiModelProperty("名称，匹配查询")
    private String name;

    /**
     * 查询page信息有效
     */
    @ApiModelProperty("分页和排序信息")
    private PageOrderDTO pageOrderDTO;
}