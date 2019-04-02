package com.zhulong.business.system.busservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 描述：系统参数分类保存DTO
 *
 * @author 初。
 * @date 2019-03-20 10:01:25
 */
@ApiModel("系统参数分类 - 保存DTO")
@Data
public class SystemParamTypeListDTO {

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("系统参数分类编号")
    private String code;

    @ApiModelProperty("系统参数分类名称")
    private String name;

}
