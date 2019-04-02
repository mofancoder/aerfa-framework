package com.zhulong.framework.autoconfig;

import com.zhulong.framework.common.spring.bean.ConfigValueBean;
import com.zhulong.framework.common.spring.mvc.GlobalExceptionAdvise;
import com.zhulong.framework.common.spring.mvc.WhiteIpListFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 通用包中需要注入的bean的自动装配
 * Created by shanb on 2019-2-19.
 */
@Configuration
public class CommonAutoConfiguration {

    /**
     * 注入此bean，用于通过静态常量类获取配置文件中的信息
     * 主要用于扩展框架不能注入spring的bean，而需要获取一些配置文件中的信息，如applicaiton.name
     */
    @Bean
    @ConditionalOnClass(ConfigValueBean.class)
    @ConditionalOnMissingBean(ConfigValueBean.class)
    public ConfigValueBean configValueBean(){
        return new ConfigValueBean();
    }


    @Bean
    @ConditionalOnClass(GlobalExceptionAdvise.class)
    @ConditionalOnMissingBean(GlobalExceptionAdvise.class)
    @ConditionalOnWebApplication
    @ConditionalOnProperty(value = "zhulong.global.exception.advise.enable",havingValue = "true")
    public GlobalExceptionAdvise globalExceptionAdvise(){
        return new GlobalExceptionAdvise();
    }


    @Bean
    @ConditionalOnClass(WhiteIpListFilter.class)
    @ConditionalOnProperty(value = "zhulong.white-ip.enable",havingValue = "true")
    public FilterRegistrationBean whiteIpListFilterRegistrationBean(@Value("${zhulong.white-ip.value:127.0.0.1}")String whiteIps){
        WhiteIpListFilter whiteIpListFilter = new WhiteIpListFilter(whiteIps);
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(whiteIpListFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}