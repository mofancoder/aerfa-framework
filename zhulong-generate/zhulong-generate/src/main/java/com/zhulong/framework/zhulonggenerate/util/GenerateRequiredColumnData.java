package com.zhulong.framework.zhulonggenerate.util;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by shanb on 2019-3-25.
 */
@Getter
@Setter
public class GenerateRequiredColumnData {

    private String columnName; //列名称

    private String propertyName;  //属性名

    private String javaTypeName;   //类型

    private String remarks; //注释

    private Boolean isRequired; //是否必须

    private Set<String> validateAnnotion = new HashSet<>();//校验的注解-保存和更新方法上

    private Boolean isQueryParam; //是否查询参数

    private Boolean isSaveData;//是否保存的数据

    private Boolean isUpdateData;//是否更新的数据

    private Boolean isListData;//是否列表显示的数据

    private Boolean isShowData;//是否查看页面展示的数据
}