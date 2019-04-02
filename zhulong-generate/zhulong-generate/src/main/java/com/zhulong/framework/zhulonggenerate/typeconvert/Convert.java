package com.zhulong.framework.zhulonggenerate.typeconvert;

import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * 数据库类型转化为java类型
 * Created by shanb on 2019-3-18.
 */
public interface Convert {

    /**
     * 是否支持这种数据类型
     */
    boolean support(String dataType);

    /**
     * 根据数据类型进行转化
     */
    String convertJavaType(String dataType);

    default Set<String> getAnnotionList(String dataType,boolean isRequired){
        return Collections.emptySet();
    }
}