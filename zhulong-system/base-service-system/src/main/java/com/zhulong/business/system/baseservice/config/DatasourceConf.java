package com.zhulong.business.system.baseservice.config;

import com.zhulong.framework.autoconfig.druid.DruidDataSourceBuilder;
import com.zhulong.framework.datasource.proxy.ZhuLongDataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by shanb on 2019-3-11.
 */
@Configuration
public class DatasourceConf {

    @ConfigurationProperties("spring.datasource")
    @Bean
    public DataSource targetDatasource(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource proxyDataSource(){
        return new ZhuLongDataSourceProxy(targetDatasource(),"mysql");
    }
}