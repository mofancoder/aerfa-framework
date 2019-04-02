package com.zhulong.framework.auth.dto;

import com.zhulong.framework.common.dto.BaseDTO;
import com.zhulong.framework.common.enums.BaseEnum;
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
@Setter
@Getter
public class OrganizationBaseInfoDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String guid;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态(1:有效，0:无效)
     */
    private Short status;

    /**
     * 类型（有运营机构和交易机构-交易中心），应该对应人员类型的一级CODE.
     */
    private String typeCode;

    /**
     * 统一社会信用代码
     */
    private String unifiedCode;

    /**
     * 运营状态（0:初始状态，1：运营状态，2：停止运营）
     */
    @ApiModelProperty("运营状态（0:初始状态，1：运营状态，2：停止运营）")
    private Short busStatus;

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
