package com.zhulong.business.system.baseservice.dto.commonbussetting;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 描述：交易类型数据查询对象
 *
 * @author 初。
 * @date 2019-03-28 09:17
 */
@ApiModel("交易类型查询数据对象")
@Data
public class TransTypeSettingQueryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;

}