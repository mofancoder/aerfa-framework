package com.zhulong.business.system.busservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述：数据字典DTO
 *
 * @author 初。
 * @date 2019-03-19 02:01:42
 */
@ApiModel("数据字典-数据传输对象")
@Data
public class DataDictionaryListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("数据字典编号")
    private String code;

    @ApiModelProperty("数据字典名称")
    private String name;

    @ApiModelProperty("级别（1：平台级，2：机构级）")
    private Short suitLevel;

    @ApiModelProperty("数据字典分类主键")
    private String typeGuid;

    @ApiModelProperty("数据字典分类名称")
    private String typeName;

}
