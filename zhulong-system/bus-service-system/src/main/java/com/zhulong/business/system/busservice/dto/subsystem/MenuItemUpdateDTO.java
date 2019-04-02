package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 菜单项-更新数据接收对象
 * Created by shanb on 2019-3-25.
 */
@Getter
@Setter
@ApiModel("菜单项管理-更新数据")
public class MenuItemUpdateDTO  implements Serializable{

    @ApiModelProperty("主键")
    @NotNull
    @Size(max = 36)
    private String guid;

    @ApiModelProperty("名称")
    @NotNull
    @Size(min = 1,max = 200)
    private String name;

    @ApiModelProperty("展示名称-中文")
    @Size(max = 200)
    private String displayNameZh;

    @ApiModelProperty("展示名称-英文")
    @Size(max = 200)
    private String displayNameEn;

    @ApiModelProperty("提示名称-中文")
    @Size(max = 200)
    private String tipNameZh;

    @ApiModelProperty("提示名称-英文")
    @Size(max = 200)
    private String tipNameEn;

    @ApiModelProperty("排序号")
    private BigDecimal sortNum;

    @ApiModelProperty("状态（0：禁用，1：启用）")
    @NotNull
    private Integer status;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("小图标附件主键")
    private String smallIconGuid;

    @ApiModelProperty("大图标附件主键")
    private String bigIconGuid;

    @ApiModelProperty("是否展示")
    @NotNull
    private Boolean dispaly;

    @ApiModelProperty("触发动作（1：弹出窗口，2：打开页签，3：打开新页面，4：打开下级菜单项，5：打开下级菜单项）")
    @NotNull
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