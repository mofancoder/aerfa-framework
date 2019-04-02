package com.zhulong.business.system.baseservice.entity.commonbussetting.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
    import java.math.BigDecimal;

/**
* 描述：汇率管理-保存数据
* @author MOFAN889
* @date 2019-04-01 16:21
*/
@Getter
@Setter
@ApiModel("汇率管理-保存数据")
public class CurrencyRateSettingSaveDTO implements Serializable{


   /**
    * 源货币
    */
    @ApiModelProperty("源货币")
    @Size(min = 1,max = 36)
    @NotNull
    private String originalCurrencyGuid;

   /**
    * 目的货币
    */
    @ApiModelProperty("目的货币")
    @Size(min = 1,max = 36)
    @NotNull
    private String destCurrentcyGuid;

   /**
    * 汇率
    */
    @ApiModelProperty("汇率")
    @NotNull
    private BigDecimal rate;

   /**
    * 有效期
    */
    @ApiModelProperty("有效期")
    @NotNull
    private Long validityDay;

   /**
    * 状态
    */
    @ApiModelProperty("状态")
    @NotNull
    private Short status;

   /**
    * 备注
    */
    @ApiModelProperty("备注")
    @Size(max = 1000)
    private String remark;

}