package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 菜单管理-更新对象信息
 * Created by shanb on 2019-2-27.
 */
@Getter
@Setter
@ApiModel("惨淡管理-更新信息")
public class MenuConfigUpdateDTO extends MenuConfigSaveDTO implements Serializable {

    @NotNull
    @ApiModelProperty("主键")
    private String guid;

}