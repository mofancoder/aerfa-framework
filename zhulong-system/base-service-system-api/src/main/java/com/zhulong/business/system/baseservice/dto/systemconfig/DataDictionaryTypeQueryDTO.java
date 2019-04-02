package com.zhulong.business.system.baseservice.dto.systemconfig;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述：数据字典类型查询DTO
 *
 * @author 初。
 * @date 2019-03-20 10:00:06
 */
@ApiModel("数据字典类型 - 查询DTO")
@Data
public class DataDictionaryTypeQueryDTO implements Serializable {

    @ApiModelProperty("编号")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;

}
