package com.zhulong.business.system.busservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by shanb on 2019-1-16.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//ZklockConfig在autoconfig中进行了自动装配
//@Import(ZkLockConfig.class)
public class BusServiceSystemAppication {

    public static void main(String[] args) {
        SpringApplication.run(BusServiceSystemAppication.class,args);
    }

}