package com.zhulong.business.system.baseservice.dto.subsystem;

import com.zhulong.framework.common.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 首页管理-基础服务层数据传输对象
 * Created by shanb on 2019-2-26.
 */
@ApiModel("首页管理-数据传输")
@Getter
@Setter
public class HomePageConfigDTO extends BaseDTO implements Serializable {

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("子系统主键")
    private String subSystemGuid;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("展示名称-中文")
    private String displayNameZh;

    @ApiModelProperty("展示名称-英文")
    private String displayNameEn;

    @ApiModelProperty("提示名称-中文")
    private String tipNameZh;

    @ApiModelProperty("提示名称-英文")
    private String tipNameEn;

    @ApiModelProperty("适用业务模式")
    private Short busModel;

    @ApiModelProperty("状态")
    private Short status;

    @ApiModelProperty("连接地址")
    private String pageUrl;

    @ApiModelProperty("备注")
    private String remark;
}