package com.zhulong.business.system.baseservice.dto.commonbussetting;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述：编码生成规则DTO
 *
 * @author 初。
 * @date 2019-03-28 10:21
 */
@ApiModel("编码生成规则 - 数据传输对象")
@Data
public class CodeRuleSettingDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String guid;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 规则
     */
    @ApiModelProperty("规则")
    private String rule;

    /**
     * 状态（0：无效，1：有效）
     */
    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
