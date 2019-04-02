package com.zhulong.framework.autoconfig;

import com.zhulong.framework.auth.common.AuthUser;
import com.zhulong.framework.auth.common.AuthUserFilter;
import com.zhulong.framework.auth.common.mvc.AuthUserConvertResove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanb on 2019-2-20.
 */
@Configuration
@ConditionalOnClass(AuthUser.class)
@ConditionalOnProperty(value = "zhulong.auth.enable",havingValue = "true")
@ConditionalOnWebApplication
public class AuthCommonAutoConfig {

    @Autowired
    private WebServerApplicationContext applicationContext;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        AuthUserFilter authUserFilter = new AuthUserFilter();
        registrationBean.setFilter(authUserFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }

    @Bean
    public AuthUserConvertResove authUserParamResove(){
        return new AuthUserConvertResove();
    }


    @Configuration
    public static class AuthWebMvcConfig implements WebMvcConfigurer {

        @Autowired
        private AuthUserConvertResove authUserConvertResove;

        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
            resolvers.add(authUserConvertResove);
        }
    }

}