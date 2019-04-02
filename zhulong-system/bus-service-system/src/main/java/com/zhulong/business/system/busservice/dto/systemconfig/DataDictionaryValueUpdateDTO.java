package com.zhulong.business.system.busservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 描述：数据字典值更新DTO
 * <p>
 * Author: 初。
 * Date: 2019-3-20
 * Time: 15:28
 */
@ApiModel("数据字典值更新操作传输对象")
@Data
public class DataDictionaryValueUpdateDTO extends DataDictionaryValueSaveDTO {

    @NotNull
    @Size(min = 1)
    @ApiModelProperty("主键")
    private String guid;

}
