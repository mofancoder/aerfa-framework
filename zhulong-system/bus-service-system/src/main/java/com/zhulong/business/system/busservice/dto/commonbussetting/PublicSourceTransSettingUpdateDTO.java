package com.zhulong.business.system.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
* 描述：公共资源分类 - 更新数据接收对象
* @author 初。
* @date 2019-03-28 09:21
*/
@ApiModel("公共资源分类 - 更新数据")
@Data
public class PublicSourceTransSettingUpdateDTO extends PublicSourceTransSettingSaveDTO {

    @NotNull
    @Size(min = 1)
    @ApiModelProperty("主键")
    private String guid;

}