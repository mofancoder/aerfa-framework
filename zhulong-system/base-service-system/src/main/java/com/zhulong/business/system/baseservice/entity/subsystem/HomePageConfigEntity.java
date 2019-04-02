package com.zhulong.business.system.baseservice.entity.subsystem;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 主页管理-JPA实体对象
 * Created by shanb on 2019-2-28.
 */
@Entity
@Table(name = "HOME_PAGE_CONFIG")
@Data
public class HomePageConfigEntity extends BaseExtendEntity implements Serializable {

    /**
     * 子系统主键
     */
    @Column(name="SUB_SYSTEM_GUID")
    private String subSystemGuid;

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
     *展示名称-中文
     */
    @Column(name="DISPLAY_NAME_ZH")
    private String displayNameZh;

    /**
     * 展示名称-英文
     */
    @Column(name="DISPLAY_NAME_EN")
    private String displayNameEn;

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
     * 适用业务模式
     */
    @Column(name="BUS_MODEL")
    private Short busModel;

    /**
     * 状态
     */
    @Column(name="STATUS")
    private Short status;

    /**
     * 连接地址
     */
    @Column(name="PAGE_URL")
    private String pageUrl;

    /**
     * 备注
     */
    @Column(name="REMARK")
    private String remark;
}