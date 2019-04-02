package com.zhulong.business.system.baseservice.dto.systemconfig;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 描述：数据字典DTO
 *
 * @author 初。
 * @date 2019-03-19 02:01:42
 */
@ApiModel("数据字典-列表查询DTO")
@Data
public class DataDictionaryQueryDTO implements Serializable {

    @ApiModelProperty("数据字典编号")
    private String code;

    @ApiModelProperty("数据字典名称")
    private String name;

    @ApiModelProperty("级别（1：平台级，2：机构级）")
    private Short suitLevel;

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;

}
