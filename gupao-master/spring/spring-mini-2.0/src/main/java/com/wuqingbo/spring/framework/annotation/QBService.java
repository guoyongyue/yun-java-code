package com.wuqingbo.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by qingbowu on 2019/3/25.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QBService {

    String value() default "";
}
