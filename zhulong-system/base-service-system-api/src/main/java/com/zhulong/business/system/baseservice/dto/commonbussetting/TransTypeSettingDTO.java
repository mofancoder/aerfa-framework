package com.zhulong.business.system.baseservice.dto.commonbussetting;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：交易类型DTO
 *
 * @author 初。
 * @date 2019-03-28 09:17
 */
@ApiModel("交易类型 - 数据传输对象")
@Data
public class TransTypeSettingDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String guid;

    /**
     * 上级主键
     */
    @ApiModelProperty("上级主键")
    private String parentGuid;

    /**
     * 上级名称
     */
    @ApiModelProperty("上级名称")
    private String parentName;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private String code;

    /**
     * 简称-中文
     */
    @ApiModelProperty("简称-中文")
    private String simpleNameZh;

    /**
     * 简称-英文
     */
    @ApiModelProperty("简称-英文")
    private String simpleNameEn;

    /**
     * 全称-中文
     */
    @ApiModelProperty("全称-中文")
    private String fullNameZh;

    /**
     * 全称-英文
     */
    @ApiModelProperty("全称-英文")
    private String fullNameEn;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

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
