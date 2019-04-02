package com.zhulong.framework.autoconfig;

import com.zhulong.framework.lock.ZhulongLock;
import com.zhulong.framework.lock.spring.ZkLockConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 自动装配分布式锁
 * Created by shanb on 2019-2-19.
 */
@Configuration
@ConditionalOnClass(ZhulongLock.class)
public class ZhulongLockAutoConfiguration {

    /**
     * 自动装配基于zk的锁
     */
    @Configuration
    @ConditionalOnMissingBean(ZhulongLock.class)
    @ConditionalOnProperty("zhulong.lock.zk.addr")
    @Import(ZkLockConfig.class)
    static class ZkLockAutoConfiguration{

    }

}