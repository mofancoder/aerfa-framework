package com.zhulong.business.system.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 描述：编码生成规则 - 保存数据
 *
 * @author 初。
 * @date 2019-03-28 10:21
 */
@ApiModel("编码生成规则 - 保存数据")
@Data
public class CodeRuleSettingSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @NotNull
    @Size(min = 1, max = 100)
    @ApiModelProperty("编号")
    private String code;

    /**
     * 名称
     */
    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty("名称")
    private String name;

    /**
     * 规则
     */
    @NotNull
    @Size(min = 1, max = 200)
    @ApiModelProperty("规则")
    private String rule;

    /**
     * 状态（0：无效，1：有效）
     */
    @NotNull
    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}