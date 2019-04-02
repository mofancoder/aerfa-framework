package com.zhulong.business.system.busservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 描述：工作时间配置更新DTO
 *
 * @author 初。
 * @date 2019-03-20 10:01:09
 */
@ApiModel("工作时间配置 - 更新DTO")
@Data
public class WorkingTimeConfigUpdateDTO extends WorkingTimeConfigSaveDTO {

    @NotNull
    @Size(min = 1)
    @ApiModelProperty("主键")
    private String guid;

}
