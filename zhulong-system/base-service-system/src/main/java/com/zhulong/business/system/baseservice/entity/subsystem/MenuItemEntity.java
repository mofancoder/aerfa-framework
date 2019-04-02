package com.zhulong.business.system.baseservice.entity.subsystem;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
* 描述：菜单项管理实体
* @author shanb
* @date 2019-03-18 04:34:31
*/
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name="MENU_ITEM")
public class MenuItemEntity extends BaseExtendEntity implements Serializable  {

    private static final long serialVersionUID = 1L;


    /**
     * 上级菜单项
     */
    @Column(name="PARENT_GUID")
    private String parentGuid;

    /**
    *编码
    */
    @Column(name="CODE")
    private String code;

    /**
    *名称
    */
    @Column(name="NAME")
    private String name;

    /**
    *菜单管理主键
    */
    @Column(name="MENU_CONFIG_GUID")
    private String menuConfigGuid;

    /**
    *展示名称-中文
    */
    @Column(name="DISPLAY_NAME_ZH")
    private String displayNameZh;

    /**
    *展示名称-英文
    */
    @Column(name="DISPLAY_NAME_EN")
    private String displayNameEn;

    /**
    *提示名称-中文
    */
    @Column(name="TIP_NAME_ZH")
    private String tipNameZh;

    /**
    *提示名称-英文
    */
    @Column(name="TIP_NAME_EN")
    private String tipNameEn;

    /**
    *排序号
    */
    @Column(name="SORT_NUM")
    private java.math.BigDecimal sortNum;

    /**
    *状态（0：禁用，1：启用）
    */
    @Column(name="STATUS")
    private Integer status;

    /**
    *备注
    */
    @Column(name="REMARK")
    private String remark;

    /**
    *小图标附件主键
    */
    @Column(name="SMALL_ICON_GUID")
    private String smallIconGuid;

    /**
    *大图标附件主键
    */
    @Column(name="BIG_ICON_GUID")
    private String bigIconGuid;

    /**
    *是否展示
    */
    @Column(name="IS_DISPALY")
    private Boolean dispaly;

    /**
    *触发动作（1：弹出窗口，2：打开页签，3：打开新页面，4：打开下级菜单项，5：打开下级菜单项）
    */
    @Column(name="FIRE_ACTION")
    private Integer fireAction;

    /**
    *完整页面地址
    */
    @Column(name="FULL_PAGE_URL")
    private String fullPageUrl;

    /**
    *简单页面地址
    */
    @Column(name="SIMPLE_PAGE_URL")
    private String simplePageUrl;

    /**
    *授权地址（ANT形式的表达）
    */
    @Column(name="AUTHORIZED_URLS")
    private String authorizedUrls;

    /**
    *流程步骤编号（当菜单是流程菜单的时候，需要此值）
    */
    @Column(name="FLOW_STEP_NUM")
    private Integer flowStepNum;

    /**
    *是否虚拟步骤（流程菜单时有值）
    */
    @Column(name="IS_VIRTUAL_STEP")
    private Boolean virtualStep;

    /**
    *是否动态插入菜单位置点（流程菜单有效）
    */
    @Column(name="IS_DYNAMIC_INERT_POSITION")
    private Boolean dynamicInertPosition;


}
