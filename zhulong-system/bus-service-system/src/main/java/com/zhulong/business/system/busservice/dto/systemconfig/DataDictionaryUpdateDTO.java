package com.zhulong.business.system.busservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 描述：数据字典更新DTO
 *
 * @author 初。
 * @date 2019-03-19 02:01:42
 */
@ApiModel("数据字典更新操作传输对象")
@Data
public class DataDictionaryUpdateDTO extends DataDictionarySaveDTO {

    @NotNull
    @Size(min = 1)
    @ApiModelProperty("主键")
    private String guid;

}
