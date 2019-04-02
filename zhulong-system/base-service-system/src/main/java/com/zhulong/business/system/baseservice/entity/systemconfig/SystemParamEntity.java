package com.zhulong.business.system.baseservice.entity.systemconfig;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 描述：系统参数实体
 *
 * @author 初。
 * @date 2019-03-20 10:01:09
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "SYSTEM_PARAM")
public class SystemParamEntity extends BaseExtendEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型主键
     */
    @Column(name = "TYPE_GUID")
    private String typeGuid;

    /**
     * 编号
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 值
     */
    @Column(name = "PARAM_VALUE")
    private String paramValue;

    /**
     * 级别（1：平台级，2：机构级）
     */
    @Column(name = "SUIT_LEVEL")
    private Short suitLevel;

    /**
     * 机构主键-机构级别的，记录此主键
     */
    @Column(name = "ORGANIZATION_GUID")
    private String organizationGuid;

    /**
     * 状态（0：无效，1：有效）
     */
    @Column(name = "STATUS")
    private Short status;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

}
