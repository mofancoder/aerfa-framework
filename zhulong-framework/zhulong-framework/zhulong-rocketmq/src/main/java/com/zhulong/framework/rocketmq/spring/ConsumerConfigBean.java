package com.zhulong.framework.rocketmq.spring;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.omg.CORBA.SystemException;

import java.util.List;
import java.util.Map;

/**
 * Created by shanb on 2018-12-22.
 * 配置spring的此bean，设置init方法即可。
 */
@Getter
@Setter
public class ConsumerConfigBean {

    private String namesrvAddr;

    private Map<String,List<String>> topicTag;

    private String groupName;

    private String instanceName;

    private String messageModel;

    private String consumeFromWhere;

    private String listenerType;

    public void init()  {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
         topicTag.forEach((topic,tags) -> {
             tags.forEach((tag) -> {
                 try {
                     consumer.subscribe(topic, tag);
                 } catch (MQClientException e) {
                     throw new RuntimeException(e);
                 }
             });
         });

        consumer.setConsumeMessageBatchMaxSize(1);
        if(StringUtils.isNotEmpty(instanceName)){
            consumer.setInstanceName(instanceName);
        }
        if(StringUtils.isNotEmpty(messageModel)){
            if("BROADCASTING".equals(messageModel)) {
                consumer.setMessageModel(MessageModel.BROADCASTING);
            }
        }
        if(StringUtils.isNotEmpty(consumeFromWhere)){
            if("first".equals(consumeFromWhere)) {
                consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            }else if("time".equals(consumeFromWhere)){
                consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
            }
        }

        if("order".equals(listenerType)) {
            consumer.setMessageListener((MessageListenerOrderly) (msgList, consumeOrderlyContext) -> {
                msgList.forEach( msg->{
                    String topic1 = msg.getTopic();
                    String tags = msg.getTags();
                    RocketMQLisetenerStore.invokeMatch(topic1,tags,msg);
                });
                return ConsumeOrderlyStatus.SUCCESS;
            });
        }else{
            consumer.setMessageListener((MessageListenerConcurrently) (msgList, consumeConcurrentlyContext) -> {
                msgList.forEach(msg ->{
                    String topic1 =msg.getTopic();
                    String tags = msg.getTags();
                    RocketMQLisetenerStore.invokeMatch(topic1,tags,msg);
                });
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
        }
        try {
            consumer.start();
        } catch (MQClientException e) {
            throw new RuntimeException("connect mq failure",e);
        }
    }
}