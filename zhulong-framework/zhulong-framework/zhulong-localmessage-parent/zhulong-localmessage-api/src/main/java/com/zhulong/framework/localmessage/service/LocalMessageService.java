package com.zhulong.framework.localmessage.service;

/**
 * 本地消息的实现
 * 在同一个数据库事务中保存业务事务和消息表，利用数据库事务，保证消息的可靠性
 * 通过定时任务，进行扫描推送
 * Created by shanb on 2018-12-18.
 */
public interface LocalMessageService {


    /**
     * 保存本地消息
     * @param guid 主键
     * @param destination 消息队列目的的
     * @param content 消息内容。内容进行协商，一般情况下  携带主键即可。提供接口，给客户端消费的进行数据查询
     */
    void saveMessage(String guid, String destination, String content);


}
