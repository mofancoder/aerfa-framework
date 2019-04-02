package com.zhulong.business.system.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
* 描述：交易类型 - 更新数据接收对象
* @author 初。
* @date 2019-03-28 09:17
*/
@ApiModel("交易类型 - 更新数据")
@Data
public class TransTypeSettingUpdateDTO extends TransTypeSettingSaveDTO {

    @NotNull
    @Size(min = 1)
    @ApiModelProperty("主键")
    private String guid;

}