package com.zhulong.framework.zhulonggenerate.typeconvert;

/**
 * Created by shanb on 2019-3-18.
 */
public class IntConvert implements Convert {

    @Override
    public boolean support(String dataType) {
        if(dataType.startsWith("int")){
            return true;
        }
        return false;
    }

    @Override
    public String convertJavaType(String dataType) {
        return Integer.class.getSimpleName();
    }
}