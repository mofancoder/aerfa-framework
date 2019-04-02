package com.zhulong.framework.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by shanb on 2019-3-27.
 */
@Getter
@Setter
public class AccountInfoDTO implements Serializable {

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String guid;

    /**
     * 用户名称
     */
    @ApiModelProperty("用户名称")
    private String name;

    /**
     * 用户类型。（1:机构，2：机构的人员，3：法人，4：法人人员，5：自然人，6：专家）
     */
    @ApiModelProperty("用户类型。（1:机构，2：机构的人员，3：法人，4：法人人员，5：自然人，6：专家）")
    private Short accountType;

    /**
     * 对应的类型的数据主键
     */
    @ApiModelProperty("对应的类型的数据主键")
    private String relateGuid;

    /**
     * 账号
     */
    @ApiModelProperty("账号")
    private String account;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    private String password;

    /**
     * 状态（0：无效，1：有效，2：锁定）
     */
    @ApiModelProperty("状态（0：无效，1：有效，2：锁定）")
    private Short status;


    /**
     * 锁定开始时间
     */
    @ApiModelProperty("锁定开始时间")
    private Long lockedStartTime;

    /**
     * 锁定结束时间
     */
    @ApiModelProperty("锁定结束时间")
    private Long lockedEndTime;

    /**
     * 上次登录IP
     */
    @ApiModelProperty("上次登录IP")
    private String lastAccessIp;

    /**
     * 上次登录时间
     */
    @ApiModelProperty("上次登录时间")
    private Long lastAccessTime;

    /**
     * 登录失败次数
     */
    @ApiModelProperty("登录失败次数")
    private Integer loginFailureCount;
}