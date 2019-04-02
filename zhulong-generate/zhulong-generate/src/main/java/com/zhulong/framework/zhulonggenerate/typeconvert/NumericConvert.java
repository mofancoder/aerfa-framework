package com.zhulong.framework.zhulonggenerate.typeconvert;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by shanb on 2019-3-18.
 */
public class NumericConvert implements Convert {

    @Override
    public boolean support(String dataType) {
        if(dataType.startsWith("numeric") || dataType.startsWith("decimal")){
            return true;
        }
        return false;
    }

    @Override
    public String convertJavaType(String dataType) {
        //进行整数位和小数位的比较
        String mnStr = dataType.substring(dataType.indexOf("(")+1,dataType.length()-1);
        String[] mnArray = mnStr.split(",");
        int m=Integer.valueOf(mnArray[0]);
        int n=0;
        if(mnArray.length>1){
            n = Integer.parseInt(mnArray[1]);
        }
        if(n>0){
            //返回全名
            return BigDecimal.class.getName();
        }
        if(m<=4){
            return Short.class.getSimpleName();
        }else if(m<=9){
            return Integer.class.getSimpleName();
        }else{
            return Long.class.getSimpleName();
        }
    }
}