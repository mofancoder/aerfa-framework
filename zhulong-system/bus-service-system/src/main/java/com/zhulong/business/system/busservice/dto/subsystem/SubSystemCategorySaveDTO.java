package com.zhulong.business.system.busservice.dto.subsystem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 子系统分类-保存方法数据传输对象
 * Created by shanb on 2019-2-25.
 */
@Getter
@Setter
@ApiModel("子系统管理-保存对象")
public class SubSystemCategorySaveDTO implements Serializable {

    /**
     * 名称
     */
    @NotNull
    @Size(min = 1,max = 200)
    @ApiModelProperty(value = "名称",allowableValues = "1-200字符")
    private String name;



    /**
     * 展示名称-中文
     */
    @NotNull
    @Size(min = 1,max = 200)
    @ApiModelProperty(value = "展示名称-中文",allowableValues = "1-200字符")
    private String displayNameZh;

    /**
     * 展示名称-英文
     */
    @NotNull
    @Size(min = 1,max = 200)
    @ApiModelProperty(value = "展示名称-英文",allowableValues = "1-200字符")
    private String displayNameEn;

    /**
     * 提示名称-中文
     */
    @Size(max = 200)
    @ApiModelProperty(value = "提示名称-中文",allowableValues = "200字符",allowEmptyValue = true)
    private String tipNameZh;

    /**
     * 提示名称-英文
     */
    @Size(max = 200)
    @ApiModelProperty(value = "提示名称-英文",allowableValues = "200字符",allowEmptyValue = true)
    private String tipNameEn;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号",allowableValues = "1-99999")
    @Max(value = 99999)
    @NotNull
    private BigDecimal sortNum;

    /**
     * 是否有效
     */
    @NotNull
    @ApiModelProperty(value = "状态",allowableValues = "0:无效 1:有效")
    private Short status;

    /**
     * 描述-中文
     */
    @Size(max = 500)
    @ApiModelProperty(value = "描述-中文",allowableValues = "500个字符",allowEmptyValue = true)
    private String descZh;

    /**
     * 描述-英文
     */
    @Size(max = 500)
    @ApiModelProperty(value = "描述-英文",allowableValues = "500个字符",allowEmptyValue = true)
    private String descEn;

    /**
     * 备注
     */
    @Size(max = 500)
    @ApiModelProperty(value = "备注",allowableValues = "500个字符",allowEmptyValue = true)
    private String remark;
}