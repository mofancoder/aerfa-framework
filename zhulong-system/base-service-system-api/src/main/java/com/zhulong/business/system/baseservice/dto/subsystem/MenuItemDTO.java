package com.zhulong.business.system.baseservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import com.zhulong.framework.common.dto.BaseDTO;

import java.io.Serializable;

/**
* 描述：菜单项管理DTO
* @author shanb
* @date 2019-03-18 11:44:45
*/
@ApiModel("菜单项管理-数据传输对象")
@Setter
@Getter
public class MenuItemDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String guid;

    @ApiModelProperty("上级菜单项")
    private String parentGuid;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("菜单管理主键")
    private String menuConfigGuid;

    @ApiModelProperty("展示名称-中文")
    private String displayNameZh;

    @ApiModelProperty("展示名称-英文")
    private String displayNameEn;

    @ApiModelProperty("提示名称-中文")
    private String tipNameZh;

    @ApiModelProperty("提示名称-英文")
    private String tipNameEn;

    @ApiModelProperty("排序号")
    private java.math.BigDecimal sortNum;

    @ApiModelProperty("状态（0：禁用，1：启用）")
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("小图标附件主键")
    private String smallIconGuid;

    @ApiModelProperty("大图标附件主键")
    private String bigIconGuid;

    @ApiModelProperty("是否展示")
    private Boolean dispaly;

    @ApiModelProperty("触发动作（1：弹出窗口，2：打开页签，3：打开新页面，4：打开下级菜单项，5：打开下级菜单项）")
    private Integer fireAction;

    @ApiModelProperty("完整页面地址")
    private String fullPageUrl;

    @ApiModelProperty("简单页面地址")
    private String simplePageUrl;

    @ApiModelProperty("授权地址（ANT形式的表达）")
    private String authorizedUrls;

    @ApiModelProperty("流程步骤编号（当菜单是流程菜单的时候，需要此值）")
    private Integer flowStepNum;

    @ApiModelProperty("是否虚拟步骤（流程菜单时有值）")
    private Boolean virtualStep;

    @ApiModelProperty("是否动态插入菜单位置点（流程菜单有效）")
    private Boolean dynamicInertPosition;

}
