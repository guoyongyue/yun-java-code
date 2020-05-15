package com.wuqingbo.spring.framework.aop.support;

import com.wuqingbo.spring.framework.annotation.QBAspectAfter;
import com.wuqingbo.spring.framework.annotation.QBAspectBefore;
import com.wuqingbo.spring.framework.aop.aspect.QBAfterReturningAdviceInterceptor;
import com.wuqingbo.spring.framework.aop.aspect.QBAfterThrowingAdvice;
import com.wuqingbo.spring.framework.aop.config.QBAopConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qingbowu.
 */
public class QBAdvisedSupport {

    private Class<?> targetClazz;

    private Object targetObje;

    private QBAopConfig aopConfig;

    private Pattern poinCutClassPattern;

    private Map<Method,List<Object>> methodCache;

    public QBAdvisedSupport(QBAopConfig aopConfig) {
        this.aopConfig = aopConfig;
    }

    public Class<?> getTargetClass() {
        return this.targetClazz;
    }


    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) throws Exception {
        List<Object> cache = this.methodCache.get(method);
        if (null == cache){
            Method m = targetClass.getMethod(method.getName(),method.getParameterTypes());
            //底层逻辑，对代理方法进行一个兼容处理
            cache = methodCache.get(m);
            this.methodCache.put(m,cache);
        }
        return cache;
    }



    public void setTargetClazz(Class<?> clazz) {
        this.targetClazz = clazz;
        parse();
    }

    private void parse() {
        String pointCut = this.aopConfig.getPointCut()
                .replaceAll("\\.","\\\\.")
                .replaceAll("\\\\.\\*",".*")
                .replaceAll("\\(","\\\\(")
                .replaceAll("\\)","\\\\)");

        //public .* com.wuqingbo.spring.demo.service..*Service..*(.*)
        String pointCutForClass = pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        poinCutClassPattern = Pattern.compile("class " + pointCutForClass.substring(pointCutForClass.lastIndexOf(" ")+1));

        try {
            methodCache = new HashMap<Method, List<Object>>();
            Pattern pattern = Pattern.compile(pointCut);

            Class<?> aspectClass = Class.forName(this.aopConfig.getPointClass());
            Map<String,Method> aspectMethod = new HashMap<String,Method>();
            for (Method method : aspectClass.getMethods()) {
                aspectMethod.put(method.getName(),method);
            }

            for (Method method : this.targetClazz.getMethods()) {
                String methodStr = method.toString();
                if (methodStr.contains("throws")){
                    methodStr = methodStr.substring(0,methodStr.lastIndexOf("throws")).trim();
                }

                Matcher matcher = pattern.matcher(methodStr);
                if (matcher.matches()){
                    List<Object> advices = new LinkedList<Object>();
                    //把每一个方法包装成MethodInterceptor
                    //before
                    if(method.isAnnotationPresent(QBAspectBefore.class)){
                        advices.add(new QBAfterReturningAdviceInterceptor(aspectMethod.get(this.aopConfig.getAspectAfter()),aspectClass.newInstance()));
                    }
//                    if (!(null == this.aopConfig.getAspectBefore() || "".equals(this.aopConfig.getAspectBefore()))){
//                        advices.add(new QBMethodBeforeAdviceInterceptor(aspectMethod.get(this.aopConfig.getAspectBefore()),aspectClass.newInstance()));
//                    }
                    //after
                    if(method.isAnnotationPresent(QBAspectAfter.class)){
                        advices.add(new QBAfterReturningAdviceInterceptor(aspectMethod.get(this.aopConfig.getAspectAfter()),aspectClass.newInstance()));
                    }
//                    if (!(null == this.aopConfig.getAspectAfter() || "".equals(this.aopConfig.getAspectAfter()))){
//                        advices.add(new QBAfterReturningAdviceInterceptor(aspectMethod.get(this.aopConfig.getAspectAfter()),aspectClass.newInstance()));
//                    }
                    //afterThrowing
                    if (!(null == this.aopConfig.getAfterThrow() || "".equals(this.aopConfig.getAfterThrow()))){
                        advices.add(new QBAfterThrowingAdvice(aspectMethod.get(this.aopConfig.getAfterThrow()),aspectClass.newInstance()));
                    }
                    methodCache.put(method,advices);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Object getTargetObje() {
        return targetObje;
    }

    public void setTargetObje(Object targetObje) {
        this.targetObje = targetObje;
    }

    public boolean pointCutMatch() {
        return poinCutClassPattern.matcher(this.targetClazz.toString()).matches();
    }
}
