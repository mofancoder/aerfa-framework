package com.zhulong.framework.auth.dto;

import com.zhulong.framework.common.dto.PageOrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* 描述：账号信息表数据查询对象
* @author shanb
* @date 2019-03-27 18:36
*/
@Getter
@Setter
public class AccountInfoQueryDTO implements Serializable {


    /**
    * 用户类型。（1:机构，2：机构的人员，3：法人，4：法人人员，5：自然人，6：专家）
    */
    private Short accountType;

    /**
    * 账号
    */
    private String account;

}