package com.zhulong.framework.common.spring.bean;

import org.springframework.beans.factory.annotation.Value;

/**
 * 获取配置文件中的值,进行装配
 * Created by shanb on 2019-2-19.
 */
public class ConfigValueBean {

    public static String applicationName; //子系统名称

    @Value("${spring.application.name:UNKOWN}")
    public void setApplicationName(String applicationName){
        ConfigValueBean.applicationName = applicationName;
    }
}