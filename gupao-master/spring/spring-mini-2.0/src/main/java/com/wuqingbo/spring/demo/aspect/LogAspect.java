package com.wuqingbo.spring.demo.aspect;

import com.wuqingbo.spring.framework.aop.aspect.QBJoinPoint;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Created by qingbowu.
 */
@Slf4j
public class LogAspect {

    public void before(QBJoinPoint joinPoint){
        joinPoint.setUserAttribute("startTime_" + joinPoint.getMethod().getName(),System.currentTimeMillis());
        //这个方法中的逻辑，是由我们自己写的
        log.info("Invoker Before Method!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()));
        System.out.println("这是我写的前置通知方法");
    }

    public void after(QBJoinPoint joinPoint){
        log.info("Invoker After Method!!!" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()));
//        long startTime = (Long) joinPoint.getUserAttribute("startTime_" + joinPoint.getMethod().getName());
//        long endTime = System.currentTimeMillis();
//        System.out.println("use time :" + (endTime - startTime));
        System.out.println("这是我写的后置通知方法");
    }

    public void afterThrow(QBJoinPoint joinPoint,Throwable ex){
        log.info("出现异常" +
                "\nTargetObject:" +  joinPoint.getThis() +
                "\nArgs:" + Arrays.toString(joinPoint.getArguments()) +
                "\nThrows:" + ex.getMessage());
    }
}
