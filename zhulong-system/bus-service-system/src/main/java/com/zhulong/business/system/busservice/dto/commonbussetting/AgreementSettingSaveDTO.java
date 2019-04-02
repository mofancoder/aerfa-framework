package com.zhulong.business.system.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
* 描述：协议管理-保存数据
* @author MOFAN889
* @date 2019-04-01 18:06
*/
@Getter
@Setter
@ApiModel("协议管理-保存数据")
public class AgreementSettingSaveDTO implements Serializable{


   /**
    * 编号
    */
    @ApiModelProperty("编号")
    @NotNull
    @Size(min = 1,max = 100)
    private String code;

   /**
    * 名称
    */
    @ApiModelProperty("名称")
    @NotNull
    @Size(min = 1,max = 200)
    private String name;

   /**
    * 标题中文
    */
    @ApiModelProperty("标题中文")
    @Size(max = 200)
    private String titleZh;

   /**
    * 标题英文
    */
    @ApiModelProperty("标题英文")
    @Size(max = 200)
    private String titleEn;

   /**
    * 类型
    */
    @ApiModelProperty("类型")
    private Short type;

   /**
    * 级别
    */
    @ApiModelProperty("级别")
    @NotNull
    private Short suitLevel;

   /**
    * 机构主键
    */
    @ApiModelProperty("机构主键")
    @Size(max = 36)
    private String enterpriseGuid;

   /**
    * 状态
    */
    @ApiModelProperty("状态")
    @NotNull
    private Short status;

   /**
    * 协议地址
    */
    @ApiModelProperty("协议地址")
    @Size(max = 255)
    private String url;

   /**
    * 附件主键
    */
    @ApiModelProperty("附件主键")
    @Size(max = 36)
    private String attachGuid;

   /**
    * 内容
    */
    @ApiModelProperty("内容")
    private byte[] content;

}