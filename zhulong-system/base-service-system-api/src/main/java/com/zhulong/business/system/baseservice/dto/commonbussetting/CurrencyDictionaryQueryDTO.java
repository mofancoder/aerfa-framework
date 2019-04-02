package com.zhulong.business.system.baseservice.dto.commonbussetting;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* 描述：币种管理数据查询对象
* @author MOFAN889
* @date 2019-04-01 11:37
*/
@ApiModel("币种管理查询数据对象")
@Getter
@Setter
public class CurrencyDictionaryQueryDTO implements Serializable {

    @ApiModelProperty("分页排序信息")
    private PageOrderDTO pageOrderDTO;
}