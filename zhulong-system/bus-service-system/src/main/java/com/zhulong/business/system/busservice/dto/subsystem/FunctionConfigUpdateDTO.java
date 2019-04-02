package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
* 描述：功能管理-更新数据接收对象
* @author shanb
* @date 2019-03-26 16:18
*/
@Getter
@Setter
@ApiModel("功能管理-更新数据")
public class FunctionConfigUpdateDTO  implements Serializable{


    /**
    * 主键
    */
    @ApiModelProperty("主键")
    @Size(min = 1,max = 36)
    @NotNull
    private String guid;

    /**
    * 名称
    */
    @ApiModelProperty("名称")
    @NotNull
    @Size(min = 1,max = 200)
    private String name;

    /**
    * 展示名称-中文
    */
    @ApiModelProperty("展示名称-中文")
    @Size(max = 200)
    private String displayNameZh;

    /**
    * 展示名称-英文
    */
    @ApiModelProperty("展示名称-英文")
    @Size(max = 200)
    private String displayNameEn;

    /**
    * 提示名称-中文
    */
    @ApiModelProperty("提示名称-中文")
    @Size(max = 200)
    private String tipNameZh;

    /**
    * 提示名称-英文
    */
    @ApiModelProperty("提示名称-英文")
    @Size(max = 200)
    private String tipNameEn;

    /**
    * 排序号
    */
    @ApiModelProperty("排序号")
    @NotNull
    private BigDecimal sortNum;

    /**
    * 状态（0：禁用，1：启用）
    */
    @ApiModelProperty("状态（0：禁用，1：启用）")
    @NotNull
    private Short status;

    /**
    * 小图标附件主键
    */
    @ApiModelProperty("小图标附件主键")
    @Size(max = 36)
    private String smallIconGuid;

    /**
    * 大图标附件
    */
    @ApiModelProperty("大图标附件")
    @Size(max = 36)
    private String bigIconGuid;

    /**
    * 备注
    */
    @ApiModelProperty("备注")
    @Size(max = 500)
    private String remark;

    /**
    * 授权地址，ANT形式，以逗号分隔
    */
    @ApiModelProperty("授权地址，ANT形式，以逗号分隔")
    @Size(max = 500)
    private String authUrls;

    /**
    * 展示区域，使用大字段保存，以逗号分隔。（1：菜单区域展示，2：列表操作区展示）
    */
    @ApiModelProperty("展示区域，使用大字段保存，以逗号分隔。（1：菜单区域展示，2：列表操作区展示）")
    @NotNull
    @Size(min = 1,max = 10)
    private String showArea;

    /**
    * 是否重要功能
    */
    @ApiModelProperty("是否重要功能")
    @NotNull
    private Boolean importFunc;

    /**
    * 适用菜单页面，以逗号分隔存储。（1：完整页面，2：简略页面）
    */
    @ApiModelProperty("适用菜单页面，以逗号分隔存储。（1：完整页面，2：简略页面）")
    @NotNull
    @Size(min = 1,max = 10)
    private String suitMenuPage;

    /**
    * 是否功能组
    */
    @ApiModelProperty("是否功能组")
    @NotNull
    private Boolean funcGroup;

    /**
    * 功能组编号
    */
    @ApiModelProperty("功能组编号")
    @Size(max = 100)
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
    @NotNull
    private Short fireAction;

    /**
    * 打开外部页面地址
    */
    @ApiModelProperty("打开页面地址")
    @Size(max = 255)
    private String openUrl;

}