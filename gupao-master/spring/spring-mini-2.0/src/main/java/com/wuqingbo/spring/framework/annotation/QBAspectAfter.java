package com.wuqingbo.spring.framework.annotation;

import java.lang.annotation.*;

/**
 * Created by qingbowu on 2019/4/23.
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface QBAspectAfter {
}
