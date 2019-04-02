package com.zhulong.framework.gateway.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by shanb on 2019-1-14.
 * 用于实现gateway路由的动态刷新功能
 */
@Component
public class GatewayRefresh implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent event){
        boolean change = false;
        for (String key : event.changedKeys()) {
            if(key.indexOf("spring.cloud.gateway.routes")==0){
                change = true;
                break;
            }
        }

        if(change){
            applicationContext.publishEvent(new EnvironmentChangeEvent(event.changedKeys()));
            applicationContext.publishEvent(new RefreshRoutesEvent(event.changedKeys()));
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}