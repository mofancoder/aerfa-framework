package com.zhulong.business.system.busservice.dto.subsystem;

import com.zhulong.framework.common.dto.ZhEnglishDispayDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 子系统列表的数据传输对象
 * Created by shanb on 2019-2-26.
 */
@ApiModel("子系统列表的数据传输对象")
@Getter
@Setter
public class SubSystemListDTO {

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty(value = "子系统分类主键")
    private String categoryGuid;

    @ApiModelProperty(value = "子系统分类名称")
    private String categoryName;

    @ApiModelProperty(value = "用户类型名称")
    private ZhEnglishDispayDTO userTypeInfo;

    @ApiModelProperty(value = "业务模式-名称")
    private Short busModel;

    @ApiModelProperty("默认业务模式名称")
    private Short defaultBusModelName;

    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

    @ApiModelProperty("状态")
    private Short status;

}