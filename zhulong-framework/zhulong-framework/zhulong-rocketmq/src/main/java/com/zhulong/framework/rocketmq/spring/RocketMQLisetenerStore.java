package com.zhulong.framework.rocketmq.spring;

import org.apache.rocketmq.common.message.Message;
import org.springframework.util.ReflectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanb on 2018-12-22.
 */
public class RocketMQLisetenerStore {

    private static List<RocketMQLisetenerInfo>  listenerList = new ArrayList<>();

    public static void addListener(RocketMQLisetenerInfo info){
        listenerList.add(info);
    }

    public static void invokeMatch(String topic, String tag, Message message){
        listenerList.forEach((rocketMQLisetenerInfo)->{
            if(("".equals(rocketMQLisetenerInfo.getTopic()) || rocketMQLisetenerInfo.getTopic().equals(topic))
                    && ("".equals(rocketMQLisetenerInfo.getTag()) || rocketMQLisetenerInfo.getTag().equals(tag))){
                ReflectionUtils.invokeMethod(rocketMQLisetenerInfo.getMethod(), rocketMQLisetenerInfo.getTaget(), message);
            }
        });
    }

}