package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 子系统管理-更新数据传输对象
 * Created by shanb on 2019-2-26.
 */
@Getter
@Setter
@ApiModel("子系统管理-更新对象")
public class SubSystemUpdateDTO extends SubSystemSaveDTO implements Serializable {

    @ApiModelProperty("主键")
    @NotNull
    private String guid;
}