package com.zhulong.business.system.busservice.dto.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
    import java.math.BigDecimal;

/**
* 描述：机构管理-保存数据
* @author shanb
* @date 2019-03-27 10:14
*/
@Getter
@Setter
@ApiModel("机构管理-保存数据")
public class OrganizationBaseInfoSaveDTO implements Serializable{


   /**
    * 上级机构
    */
    @ApiModelProperty("上级机构")
    @Size(max = 36)
    private String parentGuid;

   /**
    * 名称
    */
    @ApiModelProperty("名称")
    @NotNull
    @Size(min = 1,max = 200)
    private String name;

   /**
    * 简称
    */
    @ApiModelProperty("简称")
    @Size(max = 100)
    private String simpleName;

   /**
    * 排序号
    */
    @ApiModelProperty("排序号")
    @NotNull
    private BigDecimal sortNum;

   /**
    * 状态(1:有效，0:无效)
    */
    @ApiModelProperty("状态(1:有效，0:无效)")
    @NotNull
    private Short status;

   /**
    * 类型（有运营机构和交易机构-交易中心），应该对应人员类型的一级CODE.
    */
    @ApiModelProperty("类型（有运营机构和交易机构-交易中心），应该对应人员类型的一级CODE.")
    @NotNull
    private String typeCode;

   /**
    * 统一社会信用代码
    */
    @ApiModelProperty("统一社会信用代码")
    @Size(min = 1,max = 18)
    @NotNull
    private String unifiedCode;

   /**
    * 负责人名称
    */
    @ApiModelProperty("负责人名称")
    @Size(max = 50)
    private String directorName;

   /**
    * 负责人证件类型（1：身份证）
    */
    @ApiModelProperty("负责人证件类型（1：身份证）")
    @NotNull
    private Short directorIdType;

   /**
    * 负责人证件号
    */
    @ApiModelProperty("负责人证件号")
    @Size(max = 50)
    private String directorIdNum;

   /**
    * 联系人名称
    */
    @ApiModelProperty("联系人名称")
    @Size(max = 50)
    private String contactName;

   /**
    * 联系人手机
    */
    @ApiModelProperty("联系人手机")
    @Size(max = 11)
    private String contactPhone;

   /**
    * 联系人邮箱
    */
    @ApiModelProperty("联系人邮箱")
    @Size(max = 100)
    private String contactEmail;

   /**
    * 联系人电话
    */
    @ApiModelProperty("联系人电话")
    @Size(max = 20)
    private String contactTel;

   /**
    * 联系人传真
    */
    @ApiModelProperty("联系人传真")
    @Size(max = 20)
    private String contactFax;

   /**
    * 邮编
    */
    @ApiModelProperty("邮编")
    @Size(max = 10)
    private String postcode;

   /**
    * 地址
    */
    @ApiModelProperty("地址")
    @Size(max = 500)
    private String address;

   /**
    * 备注
    */
    @ApiModelProperty("备注")
    @Size(max = 500)
    private String remark;

}