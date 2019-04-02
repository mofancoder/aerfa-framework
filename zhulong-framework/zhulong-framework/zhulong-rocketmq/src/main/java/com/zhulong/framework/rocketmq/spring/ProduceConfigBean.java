package com.zhulong.framework.rocketmq.spring;

import lombok.Getter;
import lombok.Setter;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;

/**
 * Created by shanb on 2018-12-22.
 * 设置此示例对象的参数，然后将调用的getProducer方法返回的结果设置为spring的bean
 */

@Getter
@Setter
public class ProduceConfigBean {

    private String namesrvAddr;

    private String groupName;

    private String instanceName;

    public DefaultMQProducer getProducer()  {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        producer.setNamesrvAddr(namesrvAddr);
        if(instanceName!=null){
            producer.setInstanceName(instanceName);
        }
        try {
            producer.start();
        }catch (MQClientException e){
            throw new RuntimeException("connect mq failure",e);
        }
        return producer;
    }
}