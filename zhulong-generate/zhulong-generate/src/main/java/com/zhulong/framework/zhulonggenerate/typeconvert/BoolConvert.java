package com.zhulong.framework.zhulonggenerate.typeconvert;

/**
 * Created by shanb on 2019-3-18.
 */
public class BoolConvert implements Convert {

    @Override
    public boolean support(String dataType) {
        if("bool".equals(dataType) || "bit".equals(dataType) || "boolean".equals(dataType)){
            return true;
        }
        return false;
    }

    @Override
    public String convertJavaType(String dataType) {
        return Boolean.class.getSimpleName();
    }
}