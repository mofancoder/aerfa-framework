package com.zhulong.business.system.baseservice.dto.commonbussetting;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述：行政区域数据查询对象
 * @author 初。
 * @date 2019-03-28 09:23
 */
@ApiModel("行政区域查询数据对象")
@Data
public class AreaSettingQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private String code;

    /**
     * 全称-中文
     */
    @ApiModelProperty("全称-中文")
    private String fullNameZh;

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;

}