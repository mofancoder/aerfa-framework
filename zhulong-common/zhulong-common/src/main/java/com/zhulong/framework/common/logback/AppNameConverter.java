package com.zhulong.framework.common.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.zhulong.framework.common.spring.bean.ConfigValueBean;
import org.springframework.util.StringUtils;

/**
 * 用于logback中转化模块的打印
 * @author xxc
 * @time 2019-2-20 16:12
 */
public class AppNameConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent le) {

        String appName= ConfigValueBean.applicationName;
        if(!StringUtils.isEmpty(appName)){
             return appName;
        }
        return "UNKOWN";
    }
}