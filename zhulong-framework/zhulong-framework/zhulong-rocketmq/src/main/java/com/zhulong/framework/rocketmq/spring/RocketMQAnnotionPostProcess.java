package com.zhulong.framework.rocketmq.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

/**
 * Created by shanb on 2018-12-22.
 * 将spring注册的bean，进行方法注解的判断，如果是rocketmq监听标记的方法，则进行记录，用于消费者进行调用。
 */
public class RocketMQAnnotionPostProcess implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        //查询是否存在方法标记了RocketMQStreamLister，如果是，记录寄来，用于rocketmq监听，反射。
        ReflectionUtils.doWithMethods(bean.getClass(),(m)->{
            RocketMQStreamLister annotation =  AnnotationUtils.getAnnotation(m, RocketMQStreamLister.class);
            if(annotation!=null){
                String topic = annotation.topic();
                String tag = annotation.tag();
                RocketMQLisetenerInfo info = RocketMQLisetenerInfo.builder().topic(topic).tag(tag).taget(bean).method(m).build();
                RocketMQLisetenerStore.addListener(info);
            }
        });

        return bean;
    }
}