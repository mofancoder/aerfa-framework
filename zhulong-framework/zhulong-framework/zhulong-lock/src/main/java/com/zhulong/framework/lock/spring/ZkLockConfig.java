package com.zhulong.framework.lock.spring;

import com.zhulong.framework.lock.LockProvider;
import com.zhulong.framework.lock.ZhulongLock;
import com.zhulong.framework.lock.ZkLockProvide;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * Created by shanb on 2019-1-22.
 * 提供spring的泪痣，使用注解@import导入此类即可
 */
public class ZkLockConfig {

    @Bean
    public LockProvider lockProvide(@Value("${zhulong.lock.zk.addr:\"localhost:2181\"}") String zkAddress,@Value("${zhulong.lock.zk.namespace:zhulong-lock}") String namespace){
        System.out.println("--------lock-----------------");
        LockProvider lockProvider = new ZkLockProvide(zkAddress,namespace);
        return lockProvider;
    }

    @Bean
    public ZhulongLock zhulongLock(LockProvider lockProvider){
        ZhulongLock zhulongLock = new ZhulongLock(lockProvider);
        return zhulongLock;
    }
}