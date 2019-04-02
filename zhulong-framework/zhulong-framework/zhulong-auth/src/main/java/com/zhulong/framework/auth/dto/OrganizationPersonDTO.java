package com.zhulong.framework.auth.dto;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* 描述：机构人员表DTO
* @author shanb
* @date 2019-03-27 20:45
*/
@Setter
@Getter
public class OrganizationPersonDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

   /**
    * 主键
    */
    private String guid;


    /**
    * 用户名称
    */
    private String name;

    /**
    * 所在机构主键
    */
    private String organizationGuid;

    /**
    * 证件号码
    */
    @ApiModelProperty("证件号码")
    private String idNum;


    /**
    * 状态（1：有效，0：无效）
    */
    @ApiModelProperty("状态（1：有效，0：无效）")
    private Short status;

}
