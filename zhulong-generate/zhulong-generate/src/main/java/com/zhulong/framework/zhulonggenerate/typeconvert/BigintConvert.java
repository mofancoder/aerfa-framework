package com.zhulong.framework.zhulonggenerate.typeconvert;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shanb on 2019-3-18.
 */
public class BigintConvert implements Convert {

    @Override
    public boolean support(String dataType) {
        if(dataType.startsWith("bigint")){
            return true;
        }
        return false;
    }

    @Override
    public String convertJavaType(String dataType) {
        return Long.class.getSimpleName();
    }

}