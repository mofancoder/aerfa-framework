package com.zhulong.business.system.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述：编码生成规则页面显示数据
 *
 * @author 初。
 * @date 2019-03-28 10:21
 */
@ApiModel("编码生成规则 - 查看页面显示数据")
@Data
public class CodeRuleSettingShowDTO implements Serializable {

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

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Long createTime;

    /**
     * 创建人主键
     */
    @ApiModelProperty("创建人主键")
    private String creatorGuid;

    /**
     * 创建人名称
     */
    @ApiModelProperty("创建人名称")
    private String creatorName;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Long modifyTime;

    /**
     * 修改人主键
     */
    @ApiModelProperty("修改人主键")
    private String modifierGuid;

    /**
     * 修改人名称
     */
    @ApiModelProperty("修改人名称")
    private String modifierName;

}