package com.wuqingbo.spring.framework.context;

import com.wuqingbo.spring.framework.annotation.QBAutowired;
import com.wuqingbo.spring.framework.annotation.QBController;
import com.wuqingbo.spring.framework.annotation.QBService;
import com.wuqingbo.spring.framework.aop.QBCglibAopProxy;
import com.wuqingbo.spring.framework.aop.QBAopProxy;
import com.wuqingbo.spring.framework.aop.QBJdkDynamicAopProxy;
import com.wuqingbo.spring.framework.aop.config.QBAopConfig;
import com.wuqingbo.spring.framework.aop.support.QBAdvisedSupport;
import com.wuqingbo.spring.framework.beans.QBBeanFactory;
import com.wuqingbo.spring.framework.beans.QBBeanWrapper;
import com.wuqingbo.spring.framework.beans.config.QBBeanDefinition;
//import com.wuqingbo.spring.framework.beans.config.QBBeanPostProcessor;
import com.wuqingbo.spring.framework.beans.support.QBBeanDefinitionReader;
import com.wuqingbo.spring.framework.beans.support.QBDefaultListableBeanFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by qingbowu.
 */
public class QBApplicationContext extends QBDefaultListableBeanFactory implements QBBeanFactory {

    private QBBeanDefinitionReader reader;

    private String[] configLocations;

    //单例的IOC容器缓存
    private Map<String,Object> singletonObjects = new ConcurrentHashMap<String,Object>();

    //通用的IOC容器
    private Map<String,QBBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String,QBBeanWrapper>();

    public QBApplicationContext(String ... configLocations){
        this.configLocations = configLocations;
        try {
            refresh();
        } catch (Exception e) {
                e.printStackTrace();
        }
    }

    @Override
    public void refresh() throws Exception {
        //1、定位配置文件
        reader = new QBBeanDefinitionReader(configLocations);

        //2、加载配置文件，扫描相关的类，把它们封装成BeanDefinition
        List<QBBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

        //3、注册，把配置信息放到容器里面(伪IOC容器)
        doRegisterBeanDefinitions(beanDefinitions);

        //4、把非延迟加载的类提前初始化
        doAutoWrited();
    }

    //注入，只处理非延时加载的情况
    private void doAutoWrited() {
        for (Map.Entry<String,QBBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()){
            String benaName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()){
                try {
                    getBean(benaName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }


    private void doRegisterBeanDefinitions(List<QBBeanDefinition> beanDefinitions) throws Exception {
        for (QBBeanDefinition beanDefinition : beanDefinitions){
            if (super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("The " + beanDefinition.getFactoryBeanName() +" is exists!!");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
    }


    //依赖注入从这里开始，通过读取BeanDefinition中的信息
    //然后通过反射机制创建一个实例并返回
    //Spring的做法是：不会把最原始的对象返回出去，会用一个BeanWrapper来进行一次包装
    //装饰器模式：
    //1、保留原来的OOP关系
    //2、我们需要对它进行扩展，增强(为了后面AOP做铺垫)
    @Override
    public Object getBean(String beanName) throws Exception {
        //初始化和注入分为两步操作是因为实例存在循环依赖的问题
        //1.初始化
        Object instance = instantiateBean(beanName, beanDefinitionMap.get(beanName));

//        QBBeanPostProcessor beanPostProcessor = new QBBeanPostProcessor();
//        beanPostProcessor.postProcessBeforeInitialization(instance,beanName);

        //2、把对象封装到BeanWrapper中
        QBBeanWrapper beanWrapper = new QBBeanWrapper(instance);

        //3、拿到BeanWrapper后，把BeanWrapper保存到IOC容器中
        this.factoryBeanInstanceCache.put(beanName,beanWrapper);
//        beanPostProcessor.postProcessAfterInitialization(instance,beanName);
        //4、注入
        populateBean(beanName,new QBBeanDefinition(),beanWrapper);
        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    @Override
    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    @Override
    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }


    private void populateBean(String beanName, QBBeanDefinition qbBeanDefinition, QBBeanWrapper qbBeanWrapper) {
        Object wrappedInstance = qbBeanWrapper.getWrappedInstance();

        Class<?> clazz = qbBeanWrapper.getWrappedClass();
        //判断只有加了注解的类，才执行依赖注入
        if (!(clazz.isAnnotationPresent(QBController.class) || clazz.isAnnotationPresent(QBService.class))){
            return;
        }
        //获得所有的fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(QBAutowired.class)){continue;}

            QBAutowired autowired = field.getAnnotation(QBAutowired.class);
            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)){
                autowiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);
            try {
                field.set(wrappedInstance,this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private Object instantiateBean(String beanName, QBBeanDefinition beanDefinition) {
        //1、拿到要实例化对象的类名
        String className = beanDefinition.getBeanClassName();
        //2、反射实例化，得到一个对象
        Object instance = null;
        try {
            if (this.singletonObjects.containsKey(className)){
                instance = this.singletonObjects.get(className);
            }else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                QBAdvisedSupport advised = instantionAopConfig(beanDefinition);
                advised.setTargetClazz(clazz);
                advised.setTargetObje(instance);

                if (advised.pointCutMatch()){
                    instance = createAopProxy(advised).getProxy();
                }

                this.singletonObjects.put(className,instance);
                this.singletonObjects.put(beanDefinition.getFactoryBeanName(),instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    private QBAopProxy createAopProxy(QBAdvisedSupport advised) {
        Class<?> clazz = advised.getTargetClass();
        return clazz.getInterfaces().length >0 ? new QBJdkDynamicAopProxy(advised) : new QBCglibAopProxy();
    }

    private QBAdvisedSupport instantionAopConfig(QBBeanDefinition beanDefinition) {
        QBAopConfig aopConfig = new QBAopConfig();
        aopConfig.setPointCut(this.reader.getConfig().getProperty("pointCut"));
        aopConfig.setPointClass(this.reader.getConfig().getProperty("aspectClass"));
        aopConfig.setAspectBefore(this.reader.getConfig().getProperty("aspectBefore"));
        aopConfig.setAspectAfter(this.reader.getConfig().getProperty("aspectAfter"));
        aopConfig.setAfterThrow(this.reader.getConfig().getProperty("aspectAfterThrow"));
        aopConfig.setAspectAfterThrowingName(this.reader.getConfig().getProperty("aspectAfterThrowingName"));
        return new QBAdvisedSupport(aopConfig);
    }

    public Properties getConfig(){
        return this.reader.getConfig();
    }
}
