package com.zhulong.framework.autoconfig;

import com.zhulong.framework.rocketmq.spring.ConsumerConfigBean;
import com.zhulong.framework.rocketmq.spring.RocketMQAnnotionPostProcess;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * 进行rocketmq消费者的自动装配功能。
 * Created by shanb on 2019-2-19.
 */
@Configuration
@ConditionalOnClass(ConsumerConfigBean.class)
@ConditionalOnProperty({"rocketmq.namesrvAddr","rocketmq.consumer.groupName"})
public class RocketMQConsumerConfiguration {

    //启动@RocketMQstreamLister注解
    @Bean
    @ConditionalOnMissingBean(RocketMQAnnotionPostProcess.class)
    public BeanPostProcessor rocketMQPostProcess(){
        System.out.println("------rocketMQPostProcess-------");
        return new RocketMQAnnotionPostProcess();
    }

    @Bean
    @ConditionalOnMissingBean(RocketMqConsumerPropertiesBean.class)
    public RocketMqConsumerPropertiesBean rocketMqConsumerPropertiesBean(){
        return new RocketMqConsumerPropertiesBean();
    }

    @ConditionalOnMissingBean(ConsumerConfigBean.class)
    @Bean(initMethod = "init")
    public ConsumerConfigBean demoTestConsumer(@Value("${rocketmq.namesrvAddr}")String namesrvAddr, RocketMqConsumerPropertiesBean propertiesBean){
        ConsumerConfigBean consumerConfigBean = new ConsumerConfigBean();
        consumerConfigBean.setNamesrvAddr(namesrvAddr);
        consumerConfigBean.setGroupName(propertiesBean.getGroupName());
        consumerConfigBean.setTopicTag(propertiesBean.getTopicTag());
        consumerConfigBean.setGroupName(propertiesBean.getGroupName());
        consumerConfigBean.setInstanceName(propertiesBean.getInstanceName());
        consumerConfigBean.setMessageModel(propertiesBean.getMessageModel());
        consumerConfigBean.setConsumeFromWhere(propertiesBean.getConsumeFromWhere());
        consumerConfigBean.setListenerType(propertiesBean.getListenerType());
        return consumerConfigBean;
    }


    /**
     * 消费者配置的bean
     */
    @ConfigurationProperties(prefix = "rocketmq.consumer")
    @Getter
    @Setter
    public static class RocketMqConsumerPropertiesBean {

        private Map<String,List<String>> topicTag;

        private String groupName;

        private String instanceName;

        private String messageModel;

        private String consumeFromWhere;

        private String listenerType;
    }
}