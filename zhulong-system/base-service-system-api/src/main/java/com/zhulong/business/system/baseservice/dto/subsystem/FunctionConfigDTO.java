package com.zhulong.business.system.baseservice.dto.subsystem;

import com.zhulong.framework.common.dto.BaseDTO;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
* 描述：功能管理DTO
* @author shanb
* @date 2019-03-26 11:38
*/
@ApiModel("功能管理-数据传输对象")
@Setter
@Getter
public class FunctionConfigDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键
    */
    @ApiModelProperty("主键")
    private String guid;

    /**
     * 上级主键
     */
    @ApiModelProperty("上级主键")
    private String parentGuid;

    /**
    * 编码
    */
    @ApiModelProperty("编码")
    private String code;

    /**
    * 名称
    */
    @ApiModelProperty("名称")
    private String name;

    /**
    * 菜单项
    */
    @ApiModelProperty("菜单项")
    private String menuItemGuid;

    /**
    * 菜单主键-做冗余
    */
    @ApiModelProperty("菜单主键-做冗余")
    private String menuConfigGuid;

    /**
    * 展示名称-中文
    */
    @ApiModelProperty("展示名称-中文")
    private String displayNameZh;

    /**
    * 展示名称-英文
    */
    @ApiModelProperty("展示名称-英文")
    private String displayNameEn;

    /**
    * 提示名称-中文
    */
    @ApiModelProperty("提示名称-中文")
    private String tipNameZh;

    /**
    * 提示名称-英文
    */
    @ApiModelProperty("提示名称-英文")
    private String tipNameEn;

    /**
    * 排序号
    */
    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

    /**
    * 状态（0：禁用，1：启用）
    */
    @ApiModelProperty("状态（0：禁用，1：启用）")
    private Short status;

    /**
    * 小图标附件主键
    */
    @ApiModelProperty("小图标附件主键")
    private String smallIconGuid;

    /**
    * 大图标附件
    */
    @ApiModelProperty("大图标附件")
    private String bigIconGuid;

    /**
    * 备注
    */
    @ApiModelProperty("备注")
    private String remark;

    /**
    * 授权地址，ANT形式，以逗号分隔
    */
    @ApiModelProperty("授权地址，ANT形式，以逗号分隔")
    private String authUrls;

    /**
    * 展示区域，使用大字段保存，以逗号分隔。（1：菜单区域展示，2：列表操作区展示）
    */
    @ApiModelProperty("展示区域，使用大字段保存，以逗号分隔。（1：菜单区域展示，2：列表操作区展示）")
    private String showArea;

    /**
    * 是否重要功能
    */
    @ApiModelProperty("是否重要功能")
    private Boolean importFunc;

    /**
    * 适用菜单页面，以逗号分隔存储。（1：完整页面，2：简略页面）
    */
    @ApiModelProperty("适用菜单页面，以逗号分隔存储。（1：完整页面，2：简略页面）")
    private String suitMenuPage;

    /**
    * 是否功能组
    */
    @ApiModelProperty("是否功能组")
    private Boolean funcGroup;

    /**
    * 功能组编号
    */
    @ApiModelProperty("功能组编号")
    private String funcGroupCode;

    /**
    * 是否默认功能
    */
    @ApiModelProperty("是否默认功能")
    private Boolean defaultFunc;

    /**
    * 触发动作（1：弹出窗口，2：打开页签，3：打开新页面，4：其它）
    */
    @ApiModelProperty("触发动作（1：弹出窗口，2：打开页签，3：打开新页面，4：其它）")
    private Short fireAction;

    /**
    * 打开页面地址
    */
    @ApiModelProperty("打开页面地址")
    private String openUrl;

}
