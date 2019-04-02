package com.zhulong.business.system.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 描述：编码生成规则 - 更新数据接收对象
 *
 * @author 初。
 * @date 2019-03-28 10:21
 */
@ApiModel("编码生成规则 - 更新数据")
@Data
public class CodeRuleSettingUpdateDTO extends CodeRuleSettingSaveDTO {

    @NotNull
    @Size(min = 1)
    @ApiModelProperty("主键")
    private String guid;

}