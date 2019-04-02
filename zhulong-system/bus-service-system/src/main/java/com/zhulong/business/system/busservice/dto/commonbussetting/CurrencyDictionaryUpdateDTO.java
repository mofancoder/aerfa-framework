package com.zhulong.business.system.busservice.dto.commonbussetting;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
* 描述：币种管理-更新数据接收对象
* @author MOFAN889
* @date 2019-04-01 11:37
*/
@Getter
@Setter
@ApiModel("币种管理-更新数据")
public class CurrencyDictionaryUpdateDTO  implements Serializable{

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @Size(min = 1,max = 36)
    @NotNull
    private String guid;


    /**
    * 编号
    */
    @ApiModelProperty("编号")
    @NotNull
    @Size(min = 1,max = 100)
    private String code;

    /**
    * 简称-中文
    */
    @ApiModelProperty("简称-中文")
    @Size(max = 100)
    private String simpleNameZh;

    /**
    * 简称-英文
    */
    @ApiModelProperty("简称-英文")
    @Size(max = 100)
    private String simpleNameEn;

    /**
    * 全称-中文
    */
    @ApiModelProperty("全称-中文")
    @Size(max = 200)
    private String fullNameZh;

    /**
    * 全称-英文
    */
    @ApiModelProperty("全称-英文")
    @Size(max = 200)
    private String fullNameEn;

    /**
    * 符号
    */
    @ApiModelProperty("符号")
    @Size(max = 10)
    private String symbol;

    /**
    * 是否本币
    */
    @ApiModelProperty("是否本币")
    @NotNull
    private Boolean localCurrency;

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