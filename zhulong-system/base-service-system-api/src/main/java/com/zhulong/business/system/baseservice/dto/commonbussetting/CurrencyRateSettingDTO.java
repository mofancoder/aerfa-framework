package com.zhulong.business.system.baseservice.dto.commonbussetting;

import com.zhulong.framework.common.dto.BaseDTO;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* 描述：汇率管理DTO
* @author MOFAN889
* @date 2019-04-01 16:21
*/
@ApiModel("汇率管理-数据传输对象")
@Setter
@Getter
public class CurrencyRateSettingDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

   /**
    * 主键
    */
    @ApiModelProperty("主键")
    private String guid;


    /**
    * 源货币
    */
    @ApiModelProperty("源货币")
    private String originalCurrencyGuid;

    /**
    * 目的货币
    */
    @ApiModelProperty("目的货币")
    private String destCurrentcyGuid;

    /**
    * 汇率
    */
    @ApiModelProperty("汇率")
    private BigDecimal rate;

    /**
    * 有效期
    */
    @ApiModelProperty("有效期")
    private Long validityDay;

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
