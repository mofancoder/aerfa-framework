package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 首页管理-更新功能数据传输对象
 * Created by shanb on 2019-2-27.
 */
@Getter
@Setter
@ApiModel("更新功能数据传输对象")
public class HomePageConfigUpdateDTO extends HomePageConfigSaveDTO {

    @NotNull
    @Size(min = 1)
    @ApiModelProperty("主键")
    private String guid;
}