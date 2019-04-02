package com.zhulong.framework.common.util;


import java.util.List;
import java.util.stream.Collectors;

/**
 * 用于dto转化的类
 * Created by shanb on 2019-1-21.
 */
public class POJOConvertUtil {

    /**
     * 用于类型两个DTO的类型转化
     */
    public static<S,T> T convert(S source,Class<T> tclazz) {
        T target;
        try {
            target = tclazz.newInstance();
            BeanUtils.copy(target,source);
        } catch (Exception e) {
            throw new RuntimeException("CONVERT.ERROR",e);
        }
        return target;
    }

    /**
     * 两个不同DTO类型，列表的转化
     */
    public static<S,T> List<T> convertList(List<S> sourceList,Class<T> tclazz){
       return sourceList.stream().map(s->convert(s,tclazz)).collect(Collectors.toList());
    }

}