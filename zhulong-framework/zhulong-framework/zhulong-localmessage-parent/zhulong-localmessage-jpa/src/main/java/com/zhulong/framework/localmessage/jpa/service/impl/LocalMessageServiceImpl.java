package com.zhulong.framework.localmessage.jpa.service.impl;


import com.zhulong.framework.localmessage.jpa.dao.LocalMessageDao;
import com.zhulong.framework.localmessage.jpa.entity.LocalMessage;
import com.zhulong.framework.localmessage.jpa.service.SendMessageService;
import com.zhulong.framework.localmessage.service.LocalMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shanb on 2018-12-18.
 */
@Transactional
@Service
@Slf4j
public class LocalMessageServiceImpl implements LocalMessageService,SendMessageService {

    @Autowired
    private LocalMessageDao localMessageDao;

    @Autowired
    private DefaultMQProducer mqProducer;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public void saveMessage(String guid, String destination, String content) {
        LocalMessage localMessage = new LocalMessage();
        localMessage.setGuid(guid);
        localMessage.setCreateTime(System.currentTimeMillis());
        localMessage.setLastPushTime(System.currentTimeMillis());
        localMessage.setDestination(destination);
        localMessage.setRetryTimes(0);
        localMessage.setMessage(content);
        localMessage.setStatus(LocalMessage.StatusEnum.NOT_PUSH.getCode());
        localMessageDao.save(localMessage);
    }

    @Override
    public Boolean sendMessageToMQ(LocalMessage localMessage) {
        Boolean mqResult = false;
        String errorMsg = null;
        try {
            SendResult sendResult = mqProducer.send(new Message(appName,localMessage.getDestination(),localMessage.getMessage().getBytes(RemotingHelper.DEFAULT_CHARSET)));
            if(sendResult.getSendStatus()== SendStatus.SEND_OK){
                mqResult = true;
            }else{
                mqResult = false;
                errorMsg = sendResult.getSendStatus().toString();
            }
        } catch (Exception e){
           log.error(e.getMessage(),e);
            errorMsg = e.getMessage();
        }
        if(mqResult){
            localMessage.setStatus(LocalMessage.StatusEnum.HAS_PUSH.getCode());
            localMessage.setFailureReason(null);
        }else{
            Integer failureCount = localMessage.getRetryTimes()+1;
            localMessage.setRetryTimes(failureCount);
            if(failureCount>=SEND_MAXTIME){
                localMessage.setStatus(LocalMessage.StatusEnum.FAIL_PUSH.getCode());
            }
            localMessage.setFailureReason(errorMsg);
            localMessage.setLastPushTime(System.currentTimeMillis());
        }
        localMessageDao.update(localMessage);
        return mqResult;
    }

    @Override
    public List<LocalMessage> getNeedPushList() {
        return localMessageDao.queryListByStatus(LocalMessage.StatusEnum.NOT_PUSH.getCode(),PageRequest.of(0,MAX_FETCH_NUMBER));
    }
}