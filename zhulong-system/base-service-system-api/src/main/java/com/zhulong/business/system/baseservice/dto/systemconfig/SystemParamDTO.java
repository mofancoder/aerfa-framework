package com.zhulong.business.system.baseservice.dto.systemconfig;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述：系统参数DTO
 *
 * @author 初。
 * @date 2019-03-20 10:01:09
 */
@ApiModel("系统参数-数据传输对象")
@Data
public class SystemParamDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("类型主键")
    private String typeGuid;

    @ApiModelProperty("类型名称")
    private String typeName;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("值")
    private String paramValue;

    @ApiModelProperty("级别（1：平台级，2：机构级）")
    private Short suitLevel;

    @ApiModelProperty("机构主键-机构级别的，记录此主键")
    private String organizationGuid;

    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    @ApiModelProperty("备注")
    private String remark;

}
