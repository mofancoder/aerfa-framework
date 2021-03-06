package com.zhulong.business.system.baseservice.dto.organization;

import com.zhulong.framework.common.dto.BaseDTO;

import java.math.BigDecimal;

import com.zhulong.framework.common.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 描述：机构管理DTO
 *
 * @author shanb
 * @date 2019-03-27 10:14
 */
@ApiModel("机构管理-数据传输对象")
@Setter
@Getter
public class OrganizationBaseInfoDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String guid;


    /**
     * 上级机构
     */
    @ApiModelProperty("上级机构")
    private String parentGuid;

    /**
     * 编号
     */
    @ApiModelProperty("编号")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 简称
     */
    @ApiModelProperty("简称")
    private String simpleName;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

    /**
     * 状态(1:有效，0:无效)
     */
    @ApiModelProperty("状态(1:有效，0:无效)")
    private Short status;

    /**
     * 类型（有运营机构和交易机构-交易中心），应该对应人员类型的一级CODE.
     */
    @ApiModelProperty("类型（有运营机构和交易机构-交易中心），应该对应人员类型的一级CODE.")
    private String typeCode;

    /**
     * 统一社会信用代码
     */
    @ApiModelProperty("统一社会信用代码")
    private String unifiedCode;

    /**
     * 负责人名称
     */
    @ApiModelProperty("负责人名称")
    private String directorName;

    /**
     * 负责人证件类型（1：身份证）
     */
    @ApiModelProperty("负责人证件类型（1：身份证）")
    private Short directorIdType;

    /**
     * 负责人证件号
     */
    @ApiModelProperty("负责人证件号")
    private String directorIdNum;

    /**
     * 联系人名称
     */
    @ApiModelProperty("联系人名称")
    private String contactName;

    /**
     * 联系人手机
     */
    @ApiModelProperty("联系人手机")
    private String contactPhone;

    /**
     * 联系人邮箱
     */
    @ApiModelProperty("联系人邮箱")
    private String contactEmail;

    /**
     * 联系人电话
     */
    @ApiModelProperty("联系人电话")
    private String contactTel;

    /**
     * 联系人传真
     */
    @ApiModelProperty("联系人传真")
    private String contactFax;

    /**
     * 邮编
     */
    @ApiModelProperty("邮编")
    private String postcode;

    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 运营状态（0:初始状态，1：运营状态，2：停止运营）
     */
    @ApiModelProperty("运营状态（0:初始状态，1：运营状态，2：停止运营）")
    private Short busStatus;

    /**
     * 业务模式
     */
    @ApiModelProperty("业务模式")
    private String busModeGuid;

    /**
     * 业务模式名称-冗余
     */
    @ApiModelProperty("业务模式名称-冗余")
    private String busModeName;

    public enum BusStatusEnum implements BaseEnum<Short> {
        INIT((short)0,"初始状态"),
        UNDERWAY((short)1,"运营中"),
        STOP((short)2,"停止运营");

        BusStatusEnum(Short code, String description) {
            this.code = code;
            this.description = description;
        }

        private Short code;

        private String description;

        @Override
        public Short getCode() {
            return this.code;
        }

        @Override
        public String getDescription() {
            return this.description;
        }
    }
}
