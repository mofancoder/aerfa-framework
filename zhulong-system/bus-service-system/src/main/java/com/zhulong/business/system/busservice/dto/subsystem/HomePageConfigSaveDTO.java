package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 首页配置-保存更新对象
 * Created by shanb on 2019-2-26.
 */
@Getter
@Setter
public class HomePageConfigSaveDTO {

    @ApiModelProperty("子系统主键")
    private String subSystemGuid;

    @NotNull
    @Size(min = 1,max = 200)
    @ApiModelProperty(value = "名称",allowableValues = "1-200字符")
    private String name;

    @NotNull
    @Size(min = 1,max = 200)
    @ApiModelProperty(value = "展示名称-中文",allowableValues = "1-200字符")
    private String displayNameZh;

    @Size(max = 200)
    @ApiModelProperty(value = "展示名称-英文",allowableValues = "200字符")
    private String displayNameEn;

    @Size(max = 200)
    @ApiModelProperty(value = "提示名称-中文",allowableValues = "200字符")
    private String tipNameZh;

    @Size(max = 200)
    @ApiModelProperty(value = "提示名称-英文",allowableValues = "200字符")
    private String tipNameEn;

    @NotNull
    @ApiModelProperty(value = "适用业务模式",allowableValues = "1:功能模式,2:项目模式",example = "1")
    private Short busModel;

    @ApiModelProperty(value = "状态",allowableValues = "0:无效,1:有效",example = "1")
    private Short status;

    @NotNull
    @Size(max = 255,min = 1)
    @ApiModelProperty(value = "连接地址",allowableValues = "255个字符",example = "/url")
    private String pageUrl;

    @ApiModelProperty(value = "备注",allowableValues = "500个字符")
    private String remark;
}