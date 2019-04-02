package com.zhulong.framework.localmessage.jpa.job;


import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.zhulong.framework.localmessage.jpa.entity.LocalMessage;
import com.zhulong.framework.localmessage.jpa.service.SendMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by shanb on 2018-12-18.
 */
//无需增加@Component注解，自动装配将自动加入
//@Component
@Slf4j
public class SendMsgJob implements SimpleJob {

    @Autowired
    private SendMessageService sendMessageService;

    /**
     * 定时任务配置此方法，等后期确定定时任务的实现后，进行配置即可。
     */
    public void execute(ShardingContext shardingContext)  {
//        log.info("---------------start execute SendMsgJob----------------");
        int totalCount = shardingContext.getShardingTotalCount();
        int shardingItem = shardingContext.getShardingItem();
        log.debug("执行定时任务,totalCount:{},shardingItem:{}",totalCount,shardingItem);
        List<LocalMessage> sendList = sendMessageService.getNeedPushList();
        sendList.forEach((localMessage)->{
            long lastPushTime = localMessage.getLastPushTime();
            //对查询出来的数据 取模，进行分片处理
            long curentMod = lastPushTime % totalCount;
            if(curentMod-shardingItem==0){//两者相等
                sendMessageService.sendMessageToMQ(localMessage);
            }
        });
        log.debug("执行定时任务结束,totalCount:{},shardingItem:{}",totalCount,shardingItem);
    }
}