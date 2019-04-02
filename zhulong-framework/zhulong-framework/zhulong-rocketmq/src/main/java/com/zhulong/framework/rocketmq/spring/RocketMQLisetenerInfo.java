package com.zhulong.framework.rocketmq.spring;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Method;

/**
 * Created by shanb on 2018-12-22.
 */
@Getter
@Setter
@Builder
public class RocketMQLisetenerInfo {

    private String topic;

    private String tag;

    private Object taget;

    private Method method;
}