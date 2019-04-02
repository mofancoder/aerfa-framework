package com.zhulong.business.system.baseservice.entity.commonbussetting.baseservice.dto.commonbussetting;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* 描述：协议管理DTO
* @author MOFAN889
* @date 2019-04-01 18:06
*/
@ApiModel("协议管理-数据传输对象")
@Setter
@Getter
public class AgreementSettingDTO extends BaseDTO implements Serializable {

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
    * 标题中文
    */
    @ApiModelProperty("标题中文")
    private String titleZh;

    /**
    * 标题英文
    */
    @ApiModelProperty("标题英文")
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
    private Short suitLevel;

    /**
    * 机构主键
    */
    @ApiModelProperty("机构主键")
    private String enterpriseGuid;

    /**
    * 状态
    */
    @ApiModelProperty("状态")
    private Short status;

    /**
    * 协议地址
    */
    @ApiModelProperty("协议地址")
    private String url;

    /**
    * 附件主键
    */
    @ApiModelProperty("附件主键")
    private String attachGuid;

    /**
    * 内容
    */
    @ApiModelProperty("内容")
    private UNKOWN content;
}
