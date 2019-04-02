package com.zhulong.business.system.baseservice.entity.subsystem;

import com.zhulong.framework.common.entity.BaseExtendEntity;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 描述：功能管理实体
 *
 * @author shanb
 * @date 2019-03-26 11:43
 */
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "FUNCTION_CONFIG")
public class FunctionConfigEntity extends BaseExtendEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 上级主键
     */
    @Column(name="PARENT_GUID")
    private String parentGuid;

    /**
     * 编码
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 菜单项
     */
    @Column(name = "MENU_ITEM_GUID")
    private String menuItemGuid;

    /**
     * 菜单主键-做冗余
     */
    @Column(name = "MENU_CONFIG_GUID")
    private String menuConfigGuid;

    /**
     * 展示名称-中文
     */
    @Column(name = "DISPLAY_NAME_ZH")
    private String displayNameZh;

    /**
     * 展示名称-英文
     */
    @Column(name = "DISPLAY_NAME_EN")
    private String displayNameEn;

    /**
     * 提示名称-中文
     */
    @Column(name = "TIP_NAME_ZH")
    private String tipNameZh;

    /**
     * 提示名称-英文
     */
    @Column(name = "TIP_NAME_EN")
    private String tipNameEn;

    /**
     * 排序号
     */
    @Column(name = "SORT_NUM")
    private BigDecimal sortNum;

    /**
     * 状态（0：禁用，1：启用）
     */
    @Column(name = "STATUS")
    private Short status;

    /**
     * 小图标附件主键
     */
    @Column(name = "SMALL_ICON_GUID")
    private String smallIconGuid;

    /**
     * 大图标附件
     */
    @Column(name = "BIG_ICON_GUID")
    private String bigIconGuid;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 授权地址，ANT形式，以逗号分隔
     */
    @Column(name = "AUTH_URLS")
    private String authUrls;

    /**
     * 展示区域，使用大字段保存，以逗号分隔。（1：菜单区域展示，2：列表操作区展示）
     */
    @Column(name = "SHOW_AREA")
    private String showArea;

    /**
     * 是否重要功能
     */
    @Column(name = "IS_IMPORT_FUNC")
    private Boolean importFunc;

    /**
     * 适用菜单页面，以逗号分隔存储。（1：完整页面，2：简略页面）
     */
    @Column(name = "SUIT_MENU_PAGE")
    private String suitMenuPage;

    /**
     * 是否功能组
     */
    @Column(name = "IS_FUNC_GROUP")
    private Boolean funcGroup;

    /**
     * 功能组编号
     */
    @Column(name = "FUNC_GROUP_CODE")
    private String funcGroupCode;

    /**
     * 是否默认功能
     */
    @Column(name = "IS_DEFAULT_FUNC")
    private Boolean defaultFunc;

    /**
     * 触发动作（1：弹出窗口，2：打开页签，3：打开新页面，4：其它）
     */
    @Column(name = "FIRE_ACTION")
    private Short fireAction;

    /**
     * 打开页面地址
     */
    @Column(name = "OPEN_URL")
    private String openUrl;

}
