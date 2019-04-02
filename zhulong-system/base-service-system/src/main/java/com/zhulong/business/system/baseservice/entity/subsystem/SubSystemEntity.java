package com.zhulong.business.system.baseservice.entity.subsystem;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by shanb on 2019-2-26.
 */
@Entity
@Table(name = "SUB_SYSTEM")
@Data
public class SubSystemEntity extends BaseExtendEntity {

    /**
     *编号
     */
    @Column(name="CODE")
    private String code;

    /**
     * 名称
     */
    @Column(name="NAME")
    private String name;

    /**
     *  子系统分类主键
     */
    @Column(name="CATEGORY_GUID")
    private String categoryGuid;

    /**
     *  展示名称-中文
     */
    @Column(name="DISPLAY_NAME_ZH")
    private String displayNameZh;

    /**
     * 展示名称-英文
     */
    @Column(name="DISPAY_NAME_EN")
    private String displayNameEn;

    /**
     *提示名称-中文
     */
    @Column(name="TIP_NAME_ZH")
    private String tipNameZh;

    /**
     * 提示名称-英文
     */
    @Column(name="TIP_NAME_EN")
    private String tipNameEn;

    /**
     * 排序
     */
    @Column(name="SORT_NUM")
    private BigDecimal sortNum;

    /**
     * 子系统类型
     */
    @Column(name="SYSTEM_TYPE")
    private String systemType;

    /**
     * 业务模式
     */
    @Column(name="BUS_MODEL")
    private Short busModel;

    /**
     * 默认业务模式
     */
    @Column(name="DEFAULT_BUS_MODEL")
    private Short defaultBusModel;

    /**
     * 用户类型
     */
    @Column(name="USER_TYPE")
    private String userType;

    /**
     * 状态
     */
    @Column(name="STATUS")
    private Short status;

    /**
     * 小图标
     */
    @Column(name="SMALL_ICON_GUID")
    private String smallIconGuid;

    /**
     * 大图标
     */
    @Column(name="BIG_ICON_GUID")
    private String bigIconGuid;

    /**
     * 背景图片
     */
    @Column(name="BACKGROUD_PIC_GUID")
    private String backGroudPicGuid;

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
}