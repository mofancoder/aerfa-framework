package com.zhulong.business.system.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
* 描述：国民经济行业分类 - 更新数据接收对象
* @author 初。
* @date 2019-03-28 09:18
*/
@ApiModel("国民经济行业分类 - 更新数据")
@Data
public class IndustryTypeSettingUpdateDTO extends IndustryTypeSettingSaveDTO {

    @NotNull
    @Size(min = 1)
    @ApiModelProperty("主键")
    private String guid;

}