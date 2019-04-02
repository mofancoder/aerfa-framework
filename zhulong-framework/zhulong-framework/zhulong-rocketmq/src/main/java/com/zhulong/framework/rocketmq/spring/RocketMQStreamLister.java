package com.zhulong.framework.rocketmq.spring;

import java.lang.annotation.*;

/**
 * Created by shanb on 2018-12-22.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface RocketMQStreamLister {

    String topic() ;

    String tag() default "";
}
