package com.zhulong.business.system.baseservice.dto.systemconfig;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：数据字典DTO
 *
 * @author 初。
 * @date 2019-03-19 02:01:42
 */
@ApiModel("数据字典-数据传输对象")
@Data
public class DataDictionaryDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("数据字典分类主键")
    private String typeGuid;

    @ApiModelProperty("数据字典分类名称")
    private String typeName;

    @ApiModelProperty("数据字典编号")
    private String code;

    @ApiModelProperty("数据字典名称")
    private String name;

    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

    @ApiModelProperty("级别（1：平台级，2：机构级）")
    private Short suitLevel;

    @ApiModelProperty("机构主键，如果为机构级别，则记录机构的数据字典。")
    private String organizationGuid;

    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    @ApiModelProperty("备注")
    private String remark;

}
