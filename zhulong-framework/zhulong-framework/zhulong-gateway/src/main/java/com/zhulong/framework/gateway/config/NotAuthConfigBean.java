package com.zhulong.framework.gateway.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shanb on 2019-1-11.
 */
@ConfigurationProperties("auth.not-auth")
@Getter
@Setter
@Component
public class NotAuthConfigBean implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private List<String> urls = new ArrayList<>();

    @ApolloConfigChangeListener
    public void onChange(ConfigChangeEvent event){
        boolean unitTestChange = false;
        for (String key : event.changedKeys()) {
            if(key.indexOf("auth.not-auth")==0){
                unitTestChange = true;
                break;
            }
        }

        if(unitTestChange){
            System.out.println("刷新环境变量");
            applicationContext.publishEvent(new EnvironmentChangeEvent(event.changedKeys()));
        }
    }


}