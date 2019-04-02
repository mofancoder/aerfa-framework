package com.zhulong.business.system.baseservice.dto.systemconfig;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述：系统参数查询DTO
 *
 * @author 初。
 * @date 2019-03-20 10:01:09
 */
@ApiModel("系统参数 - 系统参数查询DTO")
@Data
public class SystemParamQueryDTO implements Serializable {

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("级别（1：平台级，2：机构级）")
    private Short suitLevel;

    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;

}
