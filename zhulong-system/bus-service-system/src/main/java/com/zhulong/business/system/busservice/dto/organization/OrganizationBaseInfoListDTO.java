package com.zhulong.business.system.busservice.dto.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
    import java.math.BigDecimal;

/**
 * 描述：机构管理列表显示数据
 * @author shanb
 * @date 2019-03-27 10:14
 */
@ApiModel("机构管理列表显示数据")
@Getter
@Setter
public class OrganizationBaseInfoListDTO implements Serializable {

   /**
    * 主键
    */
    @ApiModelProperty("主键")
    private String guid;
   /**
    * 上级机构
    */
    @ApiModelProperty("上级机构")
    private String parentGuid;

    /**
     * 上级机构名称
     */
    @ApiModelProperty("上级机构名称")
    private String parentName;
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
    * 简称
    */
    @ApiModelProperty("简称")
    private String simpleName;
   /**
    * 排序号
    */
    @ApiModelProperty("排序号")
    private BigDecimal sortNum;
   /**
    * 状态(1:有效，0:无效)
    */
    @ApiModelProperty("状态(1:有效，0:无效)")
    private Short status;
   /**
    * 类型（有运营机构和交易机构-交易中心），应该对应人员类型的一级CODE.
    */
    @ApiModelProperty("类型（有运营机构和交易机构-交易中心），应该对应人员类型的一级CODE.")
    private String typeCode;
   /**
    * 统一社会信用代码
    */
    @ApiModelProperty("统一社会信用代码")
    private String unifiedCode;
   /**
    * 运营状态（0:初始状态，1：运营状态，2：停止运营）
    */
    @ApiModelProperty("运营状态（0:初始状态，1：运营状态，2：停止运营）")
    private Short busStatus;

}