package com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
* 描述：币种管理页面显示数据
* @author MOFAN889
* @date 2019-04-01 11:37
*/
@ApiModel("币种管理-查看页面显示数据")
@Getter
@Setter
public class CurrencyDictionaryShowDTO implements Serializable {


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
    * 符号
    */
    @ApiModelProperty("符号")
    private String symbol;

   /**
    * 是否本币
    */
    @ApiModelProperty("是否本币")
    private Boolean localCurrency;

   /**
    * 状态
    */
    @ApiModelProperty("状态")
    private Short status;

   /**
    * 备注
    */
    @ApiModelProperty("备注")
    private String remark;

}