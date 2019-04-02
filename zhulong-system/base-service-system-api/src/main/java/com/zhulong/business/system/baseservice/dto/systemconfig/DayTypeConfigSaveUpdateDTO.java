package com.zhulong.business.system.baseservice.dto.systemconfig;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 描述：日期类型配置保存时使用的DTO
 *
 * @author 初。
 * @date 2019-03-20 10:00:44
 */
@ApiModel("日期类型配置 - 保存时使用的DTO")
@Data
public class DayTypeConfigSaveUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("需要保存的日期类型配置")
    private List<DayTypeConfigDTO> dayTypeConfigDTOList;

}
