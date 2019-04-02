package com.zhulong.business.system.busservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 描述：系统参数分类更新DTO
 *
 * @author 初。
 * @date 2019-03-20 10:01:25
 */
@ApiModel("系统参数分类 - 更新DTO")
@Data
public class SystemParamTypeUpdateDTO extends SystemParamTypeSaveDTO {

    @NotNull
    @Size(min = 1)
    @ApiModelProperty("主键")
    private String guid;

}
