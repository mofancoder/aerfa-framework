package com.zhulong.framework.common.annotion;

import java.lang.annotation.*;

/**
 * 定义模块code和模块名称，标记在类上。
 * Created by shanb on 2019-2-13.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Module {

    String value() default "UNKNOW";
}
