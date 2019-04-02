package com.zhulong.business.system.baseservice;

import com.zhulong.framework.common.jpa.BaseRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by shanb on 2019-1-16.
 */
@SpringBootApplication(scanBasePackages={"com.zhulong.business.system.baseservice","com.zhulong.framework.localmessage"})
@EnableDiscoveryClient
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class,basePackages={"com.zhulong.business.system.baseservice","com.zhulong.framework.localmessage"})
@EntityScan({"com.zhulong.business.system.baseservice","com.zhulong.framework.localmessage"})
public class BaseServiceSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseServiceSystemApplication.class,args);
    }

}