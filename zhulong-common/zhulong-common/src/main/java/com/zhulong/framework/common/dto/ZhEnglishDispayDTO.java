package com.zhulong.framework.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

/**
 * 包含中英文的数据对象
 * Created by shanb on 2019-2-26.
 */
@Getter
@ApiModel("用于中英文展示的对象")
@Builder
public class ZhEnglishDispayDTO implements Serializable {

    @ApiModelProperty(value = "主键,可以为空",allowEmptyValue = true)
    private String guid;

    @ApiModelProperty(value = "展示名称-中文")
    private String dispayNameZh;

    @ApiModelProperty(value = "展示名称-英文")
    private String dispayNameEnglish;

}