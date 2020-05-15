package com.wuqingbo.spring.framework.webmvc.servlet;

import com.wuqingbo.spring.framework.annotation.QBRequestParameter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qingbowu
 */
public class QBHandlerAdapter {

    public boolean isSupport(Object handler){
        return handler instanceof QBHandlerMapping;
    }

    public QBModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //把方法的形参列表和request的形参列表的顺序一一对应
        Map<String,Integer> paramIndexMapping = new HashMap<String,Integer>();
        QBHandlerMapping handlerMapping = (QBHandlerMapping)handler;
        //根据方法拿到参数的注解，一个参数可能有多个注解，一个方法有多个参数，所以得到的是一个二维数组
        Annotation[][] annotations = handlerMapping.getMethod().getParameterAnnotations();
        for (int i=0;i<annotations.length;i++){
            for (Annotation a : annotations[i]){
                //解析的只是MyRequestParameter
                if (a instanceof QBRequestParameter){
                    //拿到参数名称，去http://localhost:8080/demo/query?name=zhangsan匹配
                    String paramName = ((QBRequestParameter) a).value();
                    //从req中拿到的参数列表中去找对应的key
                    if (!"".equals(paramName.trim())){
                        paramIndexMapping.put(paramName,i);
                    }
                }
            }
        }

        //提取方法中的HttpServletRequest和HttpServletResponse参数
        Class<?>[] parameterTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i=0;i<parameterTypes.length;i++){
            Class<?> parameterType = parameterTypes[i];
            if (parameterType == HttpServletRequest.class ||
                    parameterType == HttpServletResponse.class){
                paramIndexMapping.put(parameterType.getName(),i);
            }
        }

        //获取请求参数
        Map<String,String[]> parameterMap = request.getParameterMap();
        //实参列表
        Object[] paramValues = new Object[parameterTypes.length];
        for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
            String value = Arrays.toString(param.getValue())
                    .replaceAll("\\[|\\]","")
                    .replaceAll("\\s","");
            if (!paramIndexMapping.containsKey(param.getKey())){continue;}
            int index = paramIndexMapping.get(param.getKey());
            paramValues[index] = caseStringValue(value,parameterTypes[index]);
        }

        if (paramIndexMapping.containsKey(HttpServletRequest.class.getName())){
            int reqIndex = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = request;
        }
        if (paramIndexMapping.containsKey(HttpServletResponse.class.getName())){
            int respIndex = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = response;
        }

        Object resultValue = handlerMapping.getMethod().invoke(handlerMapping.getController(), paramValues);
        if (null == resultValue || resultValue instanceof Void){ return null;}

        boolean isModelAndView = handlerMapping.getMethod().getReturnType() == QBModelAndView.class;
        if (isModelAndView){
            return (QBModelAndView) resultValue;
        }
        return null;
    }


    private Object caseStringValue(String value, Class<?> parameterType) {
        if (String.class == parameterType){
            return value;
        }else if (Integer.class == parameterType){
            return Integer.valueOf(value);
        }else if (Double.class == parameterType){
            return Double.parseDouble(value);
        }else {
            if (null != value){
                value.toString();
            }
            return null;
        }
        //如果还有其他类型，在这里继续加if(后面优化可以用策略模式)
    }
}
