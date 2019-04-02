package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
    import java.math.BigDecimal;

/**
 * 描述：功能管理列表显示数据
 * @author shanb
 * @date 2019-03-26 15:52
 */
@ApiModel("功能管理列表显示数据")
@Getter
@Setter
public class FunctionConfigListDTO implements Serializable {

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
     * 上级功能名称
     */
    @ApiModelProperty("上级功能名称")
    private String parentName;

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
     *菜单项名称
     */
    @ApiModelProperty("菜单项名称")
    private String menuItemName;
   /**
    * 菜单主键-做冗余
    */
    @ApiModelProperty("菜单主键-做冗余")
    private String menuConfigGuid;

    /**
     *  菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String menuConfigName;
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

}