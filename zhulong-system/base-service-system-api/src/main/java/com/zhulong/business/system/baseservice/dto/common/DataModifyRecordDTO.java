package com.zhulong.business.system.baseservice.dto.common;

import com.zhulong.framework.common.dto.BaseDTO;
import com.zhulong.framework.common.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 描述：数据修改记录DTO
 *
 * @author shanb
 * @date 2019-03-27 17:41
 */
@ApiModel("数据修改记录-数据传输对象")
@Setter
@Getter
public class DataModifyRecordDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private String guid;

    /**
     * 数据类型
     */
    @ApiModelProperty("数据类型")
    private Short dataType;

    /**
     * 数据主键-对应的业务的数据主键
     */
    @ApiModelProperty("数据主键-对应的业务的数据主键")
    private String dataGuid;

    /**
     * 旧值
     */
    @ApiModelProperty("旧值")
    private String oldValue;

    /**
     * 冗余旧值-展示值
     */
    @ApiModelProperty("冗余旧值-展示值")
    private String oldValueDisplay;

    /**
     * 新值
     */
    @ApiModelProperty("新值")
    private String newValue;

    /**
     * 冗余新值的展示值
     */
    @ApiModelProperty("冗余新值的展示值")
    private String newValueDisplay;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 扩展信息,特殊业务，可通过扩展信息进行扩展
     */
    @ApiModelProperty("扩展信息,特殊业务，可通过扩展信息进行扩展")
    private String extInfo;

    @ApiModelProperty(value = "创建时间", allowEmptyValue = true)
    private Long createTime;

    @ApiModelProperty(value = "创建人主键", allowEmptyValue = true)
    private String creatorGuid;

    @ApiModelProperty(value = "创建人名称", allowEmptyValue = true)
    private String creatorName;

    public enum DataTypeEnum implements BaseEnum<Short> {
        ORG_MODIFY_BUS_STATUS((short)1,"机构修改业务状态");

        DataTypeEnum(Short code, String description) {
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
