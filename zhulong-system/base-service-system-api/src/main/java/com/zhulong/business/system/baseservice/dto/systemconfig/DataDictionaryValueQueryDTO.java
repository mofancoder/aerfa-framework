package com.zhulong.business.system.baseservice.dto.systemconfig;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述：数据字典值查询DTO
 *
 * @author 初。
 * @date 2019-03-20 09:59:31
 */
@ApiModel("数据字典值 - 查询DTO")
@Data
public class DataDictionaryValueQueryDTO implements Serializable {

    @ApiModelProperty("字典主键")
    private String dictionaryGuid;

    @ApiModelProperty("状态（0：无效，1：有效）")
    private Short status;

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;

}
