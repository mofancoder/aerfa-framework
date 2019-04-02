package com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

/**
* 描述：汇率管理页面显示数据
* @author MOFAN889
* @date 2019-04-01 16:21
*/
@ApiModel("汇率管理-查看页面显示数据")
@Getter
@Setter
public class CurrencyRateSettingShowDTO implements Serializable {


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