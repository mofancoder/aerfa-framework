package com.zhulong.business.system.baseservice.dto.commonbussetting;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* 描述：协议管理数据查询对象
* @author MOFAN889
* @date 2019-04-01 17:59
*/
@ApiModel("协议管理查询数据对象")
@Getter
@Setter
public class AgreementSettingQueryDTO implements Serializable {


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

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;
}