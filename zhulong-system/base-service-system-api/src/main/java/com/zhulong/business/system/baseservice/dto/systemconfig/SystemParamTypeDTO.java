package com.zhulong.business.system.baseservice.dto.systemconfig;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：系统参数分类DTO
 *
 * @author 初。
 * @date 2019-03-20 10:01:25
 */
@ApiModel("系统参数分类-数据传输对象")
@Data
public class SystemParamTypeDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("系统参数分类编号")
    private String code;

    @ApiModelProperty("系统参数分类名称")
    private String name;

    @ApiModelProperty("系统参数分类排序号")
    private BigDecimal sortNum;

    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    @ApiModelProperty("备注")
    private String remark;

}
