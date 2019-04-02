package com.zhulong.business.system.baseservice.dto.organization;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* 描述：机构管理数据查询对象
* @author shanb
* @date 2019-03-27 10:14
*/
@ApiModel("机构管理查询数据对象")
@Getter
@Setter
public class OrganizationBaseInfoQueryDTO implements Serializable {


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
     * 状态
     */
    @ApiModelProperty("状态")
    private Short status;

    /**
     * 上级机构
     */
    @ApiModelProperty("上级机构")
    private String parentGuid;

    /**
     * 机构类型
     */
    @ApiModelProperty("机构类型")
    private String typeCode;

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;
}