package com.wuqingbo.spring.framework.webmvc.servlet;

import com.wuqingbo.spring.framework.annotation.QBController;
import com.wuqingbo.spring.framework.annotation.QBRequestMapping;
import com.wuqingbo.spring.framework.context.QBApplicationContext;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qingbowu.
 */
@Slf4j
public class QBDispatcherServlet extends HttpServlet {

    private static final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    QBApplicationContext applicationContext;

    private List<QBHandlerMapping> handlerMappings = new ArrayList<QBHandlerMapping>();

    private List<QBViewResolver> viewResolvers = new ArrayList<QBViewResolver>();

    private Map<QBHandlerMapping,QBHandlerAdapter> handlerAdapterMap = new HashMap<QBHandlerMapping,QBHandlerAdapter>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1、初始化ApplicationContext
        applicationContext = new QBApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));
        //2、初始化SpringMVC的九大组件
        initStrategies(applicationContext);
    }

    //初始化策略
    protected void initStrategies(QBApplicationContext context) {
        //多文件上传的组件
        initMultipartResolver(context);
        //初始化本地语言环境
        initLocaleResolver(context);
        //初始化模板处理器
        initThemeResolver(context);
        //handlerMapping
        initHandlerMappings(context);
        //初始化参数适配器
        initHandlerAdapters(context);
        //初始化异常拦截器
        initHandlerExceptionResolvers(context);
        //初始化视图预处理器
        initRequestToViewNameTranslator(context);
        //初始化视图转换器
        initViewResolvers(context);
        //
        initFlashMapManager(context);
    }

    private void initFlashMapManager(QBApplicationContext context) {
    }

    private void initViewResolvers(QBApplicationContext context) {
        //拿到模板的存放目录
        String templateRoot = context.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File templateRootDir = new File(templateRootPath);
        for (File template : templateRootDir.listFiles()) {
            //这里主要是为了兼容多模板，所有模仿Spring用List保存
            //这里简化了，其实只有需要一个模板就可以搞定
            //只是为了仿真，所有还是搞了个List
            this.viewResolvers.add(new QBViewResolver(templateRoot));
        }

    }

    private void initRequestToViewNameTranslator(QBApplicationContext context) {


    }

    private void initHandlerExceptionResolvers(QBApplicationContext context) {


    }

    private void initHandlerAdapters(QBApplicationContext context) {
        //把一个request请求转换成一个handler，参数都是字符串的，自动匹配到handle中的形参
        for (QBHandlerMapping handlerMapping : this.handlerMappings) {
            this.handlerAdapterMap.put(handlerMapping,new QBHandlerAdapter());
        }

    }

    private void initThemeResolver(QBApplicationContext context) {



    }


    private void initHandlerMappings(QBApplicationContext context) {

        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        try {
            for (String beanDefinitionName : beanDefinitionNames) {
                Object bean = context.getBean(beanDefinitionName);
                Class<?> clazz = bean.getClass();
                if (!clazz.isAnnotationPresent(QBController.class)){continue;}

                //保存写在类上的@QBRequestmapping("/demo")
                String baseUrl = "";
                if (clazz.isAnnotationPresent(QBRequestMapping.class)){
                    QBRequestMapping myRequestmapping = clazz.getAnnotation(QBRequestMapping.class);
                    baseUrl = myRequestmapping.value();
                }
                //获取所有的public方法
                Method[] methods = clazz.getMethods();
                for (Method method : methods){
                    if (!method.isAnnotationPresent(QBRequestMapping.class)){continue;}
                    QBRequestMapping myRequestmapping = method.getAnnotation(QBRequestMapping.class);
                    String url = ("/" + baseUrl + "/" +myRequestmapping.value().replaceAll("\\*",".*")).replaceAll("/+","/");
                    Pattern pattern = Pattern.compile(url);
                    this.handlerMappings.add(new QBHandlerMapping(pattern,bean,method));
                    log.info("Mapped : " + url + "," + method.getName() );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void initLocaleResolver(QBApplicationContext context) {
    }

    private void initMultipartResolver(QBApplicationContext context) {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            this.doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("500 Exception,Detail:" + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1、从request中获取url，然后找到url匹配的HandlerMapping
        QBHandlerMapping handler = getHandler(req);

        if (null == handler){
            processDispatchResult(req,resp,new QBModelAndView("404"));
            return;
        }
        //2、准备调用前的参数
        QBHandlerAdapter ha =  getHandlerAdapter(handler);

        //3、真正的调用方法,返回QBModelAndView
        QBModelAndView mv = ha.handle(req, resp, handler);

        //4、将QBModelAndView进行渲染,然后输出
        processDispatchResult(req, resp, mv);

    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, QBModelAndView mv) throws Exception{

        if (null == mv){return;}

        if (this.viewResolvers.isEmpty()){return;}

        for (QBViewResolver viewResolver : this.viewResolvers) {
            QBView view = viewResolver.resolveViewName(mv.getViewName(), null);
            view.render(mv.getModel(),req,resp);
            return;
        }


    }

    private QBHandlerAdapter getHandlerAdapter(QBHandlerMapping handler) {
        if (this.handlerAdapterMap.isEmpty()){ return null;}
        QBHandlerAdapter ha = this.handlerAdapterMap.get(handler);
        if (ha.isSupport(handler)){
            return ha;
        }
        return null;
    }

    private QBHandlerMapping getHandler(HttpServletRequest req) {
        if (this.handlerMappings.isEmpty()){ return null;}
        //获取请求的url,绝对路径
        String url = req.getRequestURI();
        //处理成相对路径
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");

        for (QBHandlerMapping handler  : this.handlerMappings){
            Matcher matcher = handler.getPattern().matcher(url);
            if (!matcher.matches()){continue;}
            return handler;
        }
        return null;
    }


}
