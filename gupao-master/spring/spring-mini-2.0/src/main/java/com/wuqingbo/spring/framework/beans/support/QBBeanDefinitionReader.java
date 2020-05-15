package com.wuqingbo.spring.framework.beans.support;

import com.wuqingbo.spring.framework.annotation.QBController;
import com.wuqingbo.spring.framework.annotation.QBService;
import com.wuqingbo.spring.framework.beans.config.QBBeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by qingbowu.
 */
public class QBBeanDefinitionReader {

    //固定配置文件中的key，相当于xml的规范
    private static final String SCAN_PACKAGE = "scanPackage";

    private List<String> registryBeanClasses = new ArrayList<String>();

    private Properties config = new Properties();

    public QBBeanDefinitionReader(String... locations) {

        InputStream fis = null;
        try {
            //通过url定位找到其所对应的文件，转化为文件流读取
            fis = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:",""));
            config.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null != fis){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        doScanner(config.getProperty(SCAN_PACKAGE));
    }

    private void doScanner(String packageName) {

        //将所有的包路径转换为文件路径，其实就是把.替换为为/
        URL url = this.getClass().getResource("/" + packageName.replaceAll("\\.","/"));
        File dir = new File(url.getFile());
        for(File file : dir.listFiles()){
            if (file.isDirectory()){
                //如果是文件则继续递归
                doScanner(packageName + "." + file.getName());
            }else {
                if (!file.getName().endsWith(".class")){continue;}
                String classname = (packageName + "." + file.getName().replace(".class","")).trim();
                registryBeanClasses.add(classname);
            }
        }
    }

    public Properties getConfig() {
        return this.config;
    }

    //把每一个配置文件中扫描到的所有的配置信息解析成一个BeanDefinition对象，以便于之后IOC操作方便
    public List<QBBeanDefinition> loadBeanDefinitions() {
        List<QBBeanDefinition> result = new ArrayList<QBBeanDefinition>();
        try {
            for (String className : registryBeanClasses){
                Class<?> beanClass = Class.forName(className);
                //如果是一个接口，用它的实现类作为beanClassName
                if (beanClass.isInterface()){continue;}
                //beanName有三种情况:
                //1、默认是类名首字母小写
                //2、自定义名字
                //3、接口注入
                QBBeanDefinition beanDefinition = doCreateBeanDefinition(lowerFirstCase(beanClass.getSimpleName()),beanClass.getName());
                result.add(beanDefinition);

                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> inf : interfaces) {
                    //如果是多个实现类，只能覆盖
                    //为什么？因为Spring没那么智能，就是这么傻
                    //这个时候，可以自定义名字
                    result.add(doCreateBeanDefinition(inf.getName(),beanClass.getName()));
                }

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 把每一个配置信息解析成一个BeanDefinition
     * @param factoryBeanName
     * @param beanClassName
     * @return
     */
    private QBBeanDefinition doCreateBeanDefinition(String factoryBeanName,String beanClassName){
        QBBeanDefinition beanDefinition = new QBBeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    /**
     * 首字母小写
     * 利用ASCII码中大写字符与小写字符值相差32的规则实现首字母小写
     * 如果类名首字母是小写的会有问题
     * @param simpleName
     * @return
     */
    private String lowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
