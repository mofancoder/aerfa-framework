package com.zhulong.framework.auth.common.mvc;

import java.lang.annotation.*;

/**
 * 注解，用于标志此参数为authUser对象
 * Created by shanb on 2019-1-17.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthUserParam {

    boolean required() default true;

}
