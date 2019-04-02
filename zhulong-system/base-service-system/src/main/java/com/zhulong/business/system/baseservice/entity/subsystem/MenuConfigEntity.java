package com.zhulong.business.system.baseservice.entity.subsystem;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜单管理-JPA实体对象
 * Created by shanb on 2019-2-28.
 */
@Entity
@Table(name = "MENU_CONFIG")
@Data
public class MenuConfigEntity extends BaseExtendEntity implements Serializable {

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
     * 子系统主键
     */
    @Column(name="SUB_SYSTEM_GUID")
    private String subSystemGuid;

    /**
     * 排序号
     */
    @Column(name="SORT_NUM")
    private BigDecimal sortNum;

    /**
     * 展示名称-中文
     */
    @Column(name="DISPLAY_NAME_ZH")
    private String displayNameZh;

    /**
     * 展示名称-英文
     */
    @Column(name="DISPLAY_NAME_EN")
    private String displayNameEn;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private Short status;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 适用模式
     */
    @Column(name="BUS_MODEL")
    private Short busModel;

    /**
     * 展示位置
     */
    @Column(name="SHOW_POSITION")
    private Short showPosition;

    /**
     * 展示类型
     */
    @Column(name="SHOW_TYPE")
    private Short showType;

    /**
     * 是否展示序号
     */
    @Column(name="SHOW_SORT_NUM")
    private Boolean showSortNum;

    /**
     * 级别
     */
    @Column(name="LEVEL")
    private Short level;

    /**
     * 类型
     */
    @Column(name="TYPE")
    private Short type;

    /**
     * 业务流程
     */
    @Column(name="BUS_FLOW_GUID")
    private String busFlowGuid;
}