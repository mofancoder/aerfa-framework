package com.zhulong.framework.zhulonggenerate.typeconvert;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by shanb on 2019-3-18.
 */
public class VarcharConvert implements Convert {

    @Override
    public boolean support(String dataType) {
        if( dataType.startsWith("varchar") || "text".equals(dataType)){
            return true;
        }
        return false;
    }

    @Override
    public String convertJavaType(String dataType) {
        return String.class.getSimpleName();
    }

    @Override
    public Set<String> getAnnotionList(String dataType, boolean isRequired) {
        Set<String> set = new HashSet<>();
        if(dataType.startsWith("varchar") && dataType.indexOf("(")>0){
            String lengthStr = dataType.substring(dataType.indexOf("(")+1,dataType.length()-1);
            if(isRequired){
                set.add("@Size(min = 1,max = "+lengthStr+")");
            }else{
                set.add("@Size(max = "+lengthStr+")");
            }
        }
        return set;
    }
}