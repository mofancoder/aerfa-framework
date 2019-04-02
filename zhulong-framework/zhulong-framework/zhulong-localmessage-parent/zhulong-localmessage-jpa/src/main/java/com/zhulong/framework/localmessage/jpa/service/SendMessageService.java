package com.zhulong.framework.localmessage.jpa.service;


import com.zhulong.framework.localmessage.jpa.entity.LocalMessage;

import java.util.List;

/**
 * Created by shanb on 2018-12-18.
 */
public interface SendMessageService {

    Long SEND_TIMEOUT = 5*1000L;

    Integer SEND_MAXTIME = 5;

    Integer MAX_FETCH_NUMBER = 10;

    /**
     * 发送消息到消息消息中间件
     */
    Boolean sendMessageToMQ(LocalMessage localMessage);

    /**
     * 获取要推送的消息
     */
    List<LocalMessage> getNeedPushList();
}