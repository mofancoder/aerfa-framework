package com.zhulong.business.system.baseservice.dto.organization;

import com.zhulong.business.system.baseservice.dto.common.DataModifyRecordDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by shanb on 2019-3-27.
 */
@ApiModel("机构管理更新业务状态信息")
@Getter
@Setter
public class OrganizationUpdateBusStatusDTO implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String guid;

    /**
     * 业务状态
     */
    @ApiModelProperty("业务状态")
    private Short busStatus;

    /**
     * 修改记录数据
     */
    @ApiModelProperty("修改记录数据")
    private DataModifyRecordDTO modifyRecordDTO;
}