package com.zhulong.business.system.baseservice.entity.subsystem;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 子系统分类实体类
 * Created by shanb on 2019-2-25.
 */
@Entity
@Table(name = "SUB_SYSTEM_CATEGORY")
@Data
public class SubSystemCategoryEntity extends BaseExtendEntity implements Serializable {

    /**
     * 编号
     */
    @Column(name="CODE")
    private String code;

    /**
     * 名称
     */
    @Column(name="NAME")
    private String name;

    /**
     * 展示名称-中文
     */
    @Column(name="DISPLAY_NAME_ZH")
    private String displayNameZh;

    /**
     * 展示名称-英文
     */
    @Column(name="DISPLAY_NAME_EN")
    private String dispalyNameEn;

    /**
     * 提示名称-中文
     */
    @Column(name="TIP_NAME_ZH")
    private String tipNameZh;

    /**
     * 提示名称-英文
     */
    @Column(name="TIP_NAME_EN")
    private String tipNameEn;

    /**
     * 排序号
     */
    @Column(name = "SORT_NUM")
    private BigDecimal sortNum;

    /**
     * 状态
     */
    @Column(name="STATUS")
    private Short status;

    /**
     * 描述-中文
     */
    @Column(name="DESC_ZH")
    private String descZh;

    /**
     * 描述-英文
     */
    @Column(name="DESC_EN")
    private String descEn;

    /**
     * 备注
     */
    @Column(name="REMARK")
    private String remark;
}