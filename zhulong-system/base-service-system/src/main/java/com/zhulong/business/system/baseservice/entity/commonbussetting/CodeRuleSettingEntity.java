package com.zhulong.business.system.baseservice.entity.commonbussetting;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 描述：编码生成规则实体
 *
 * @author 初。
 * @date 2019-03-28 10:21
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "CODE_RULE_SETTING")
public class CodeRuleSettingEntity extends BaseExtendEntity implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 规则
     */
    @Column(name = "RULE")
    private String rule;

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
