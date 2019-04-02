package com.zhulong.framework.autoconfig;

import com.zhulong.framework.rocketmq.spring.ProduceConfigBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rocketmq的消息产生者的自动装配实现。
 * Created by shanb on 2019-2-19.
 */
@Configuration
@ConditionalOnClass(ProduceConfigBean.class)
@ConditionalOnProperty({"rocketmq.namesrvAddr","rocketmq.produce.groupName"})
@Slf4j
public class RocketMQProduceConfiguration {

    @Bean
    @ConditionalOnMissingBean(DefaultMQProducer.class)
    public DefaultMQProducer defaultMQProducer(@Value("${rocketmq.namesrvAddr}") String namesrvAddr, @Value("${rocketmq.produce.groupName}") String groupName, @Value("${rocketmq.localmessage.instanceName:''}") String instanceName){
        log.info("自动装配DefaultMQProducer");
        ProduceConfigBean configBean = new ProduceConfigBean();
        configBean.setNamesrvAddr(namesrvAddr);
        configBean.setGroupName(groupName);
        if(StringUtils.isNotEmpty(instanceName)) {
            configBean.setInstanceName(instanceName);
        }
        DefaultMQProducer defaultMQProducer = configBean.getProducer();
        return defaultMQProducer;
    }
}