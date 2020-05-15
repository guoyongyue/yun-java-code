package com.wuqingbo.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by qingbowu on 2019/3/25.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QBRequestParameter {

    String value() default "";

    boolean required() default true;
}
