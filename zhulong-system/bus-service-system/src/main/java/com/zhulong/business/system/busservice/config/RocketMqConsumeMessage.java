package com.zhulong.business.system.busservice.config;

import com.zhulong.framework.common.exception.SystemException;
import com.zhulong.framework.lock.LockHold;
import com.zhulong.framework.lock.ZhulongLock;
import com.zhulong.framework.rocketmq.spring.RocketMQStreamLister;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

/**
 * Created by shanb on 2019-1-18.
 */
@Component
public class RocketMqConsumeMessage {

    @Autowired
    private ZhulongLock zhulongLock;

    @RocketMQStreamLister(topic = "base-service-system",tag="user-create")
    public void consumeMessage(Message message){
        String userGuid = null;
        try {
            userGuid = new String(message.getBody(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new SystemException(e);
        }

         Optional<LockHold> lockHold = zhulongLock.getLock(userGuid, 1000L);
        if(lockHold.isPresent()) {
            try {
                System.out.println(userGuid);
            }finally {
                zhulongLock.releasLock(lockHold.get());
            }
        }else{
            throw new SystemException("get Lock failure");
        }

    }
}