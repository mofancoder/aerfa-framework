package com.zhulong.framework.common.util;

import com.zhulong.framework.common.annotion.Module;
import lombok.extern.slf4j.Slf4j;

/**
 * 模块工具类
 * Created by shanb on 2019-2-21.
 */
@Slf4j
public class ModuleUtils {

    private static final Integer DODULE_FOUND_DEEP = 10;

    private static final String DEFAULT_MODULE_NAME = "UNKOWN";

    private static final String PROJECT_PACKAGE = "com.zhulong";

    /**
     * 通过调用链路获取MODULE名称
     */
    public static String getModuleName(StackTraceElement[] cda){
        if(cda!=null && cda.length>0){
            Class clazz ;
            try {
                int deep = 0;
                for (StackTraceElement stackTraceElement : cda) {
                    String className = stackTraceElement.getClassName();
                    if(className.startsWith(PROJECT_PACKAGE)) {
                        if (deep < DODULE_FOUND_DEEP) {
                            clazz = Class.forName(className);
                            boolean hasModuleName = clazz.isAnnotationPresent(Module.class);
                            if (hasModuleName) {
                                Module module = (Module) clazz.getAnnotation(Module.class);
                                String moduleName = module.value();
                                return moduleName;
                            }
                            deep++;
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                log.warn("",e);
            }
        }
        return DEFAULT_MODULE_NAME;
    }

    /**
     * 获取模块名称，从调用链路寻找最近的标记module类的值
     */
    public static String getModuleName(){
        StackTraceElement cda[] = new Throwable().getStackTrace();
        return getModuleName(cda);
    }
}