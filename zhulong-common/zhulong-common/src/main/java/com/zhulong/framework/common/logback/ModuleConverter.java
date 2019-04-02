package com.zhulong.framework.common.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.zhulong.framework.common.annotion.Module;
import com.zhulong.framework.common.util.ModuleUtils;

/**
 * 用于logback中转化模块的打印。
 * Created by shanb on 2019-2-13.
 */
public class ModuleConverter extends ClassicConverter {

    private static final Integer DODULE_FOUND_DEEP = 10;

    @Override
    public String convert(ILoggingEvent le) {
        StackTraceElement[] cda = le.getCallerData();
        return ModuleUtils.getModuleName(cda);
    }
}