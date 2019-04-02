package com.zhulong.framework.common.dto;

import com.zhulong.framework.common.inteface.BaseUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础dto
 * @author xxc
 * @time 2019-2-14 17:54
 */
@Setter
@Getter
@ApiModel("基础数据传输对象")
public class BaseDTO {

    @ApiModelProperty(value = "创建时间",allowEmptyValue = true)
    private Long createTime;

    @ApiModelProperty(value = "创建人主键",allowEmptyValue = true)
    private String creatorGuid;

    @ApiModelProperty(value = "创建人名称",allowEmptyValue = true)
    private String creatorName;

    @ApiModelProperty(value = "修改时间",allowEmptyValue = true)
    private Long modifyTime;

    @ApiModelProperty(value = "修改人主键",allowEmptyValue = true)
    private String modifierGuid;

    @ApiModelProperty(value = "修改人名称",allowEmptyValue = true)
    private String modifierName;

    public void setCreateInfo(BaseUser baseUser){
        this.createTime = System.currentTimeMillis();
        this.creatorGuid = baseUser.getGuid();
        this.creatorName = baseUser.getName();
    }

    public void setModifyInfo(BaseUser baseUser){
        this.modifyTime = System.currentTimeMillis();
        this.modifierGuid = baseUser.getGuid();
        this.modifierName = baseUser.getName();
    }
}
