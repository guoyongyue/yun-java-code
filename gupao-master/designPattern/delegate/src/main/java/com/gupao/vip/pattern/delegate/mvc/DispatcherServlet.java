package com.gupao.vip.pattern.delegate.mvc;

import com.gupao.vip.pattern.delegate.mvc.controller.MemberController;
import com.gupao.vip.pattern.delegate.mvc.controller.OrderController;
import com.gupao.vip.pattern.delegate.mvc.controller.SystemController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by qingbowu on 2019/3/15.
 */
public class DispatcherServlet extends HttpServlet {
    private static ArrayList<Handler> handlerMapping = new ArrayList<Handler>();

    @Override
    public void init() throws ServletException {
        try {
            handlerMapping.add(new Handler().setController(MemberController.class.newInstance())
                    .setMethod((MemberController.class.getMethod("getMemberById",new Class[]{String.class})))
                    .setUrl("/web/getMemberById.htm"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDispatcher(req, resp);
    }


//    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String uri = req.getRequestURI();
//        String mid = req.getParameter("mid");
//
//        if ("getMemberById".equals(mid)){
//            new MemberController().getMemberById(mid);
//        }else if ("getOrderById".equals(mid)){
//            new OrderController().getOrderById(mid);
//        }else if ("logout".equals(mid)){
//            new SystemController().logout();
//        }else {
//            resp.getWriter().write("404 Not Found !!!");
//        }
//    }


    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) {

        //1、获取用户请求的 url
        // 如果按照 J2EE 的标准、每个 url 对对应一个 Serlvet，url 由浏览器输入

        //2、Servlet 拿到 url 以后，要做权衡（要做判断，要做选择）
        // 根据用户请求的 URL，去找到这个 url 对应的某一个 java 类的方法

        //3、通过拿到的 URL 去 handlerMapping（我们把它认为是策略常量）
        String uri = req.getRequestURI();
        Handler handler = null;
        for (Handler h : handlerMapping){
            if (h.equals(handler.getUrl())){
                handler = h;
                break;
            }
        }
        Object obj = null;
        try {
            //4、将具体的任务分发给 Method（通过反射去调用其对应的方法）
            obj = handler.getMethod().invoke(handler.getController(), req.getParameter("mid"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //5、获取到 Method 执行的结果，通过 Response 返回出去
//        resp.getWriter().write(obj.toString());
    }

    class Handler{

        private Object controller;
        private Method method;
        private String url;

        public Object getController() {
            return controller;
        }

        public Handler setController(Object controller) {
            this.controller = controller;
            return this;
        }

        public Method getMethod() {
            return method;
        }

        public Handler setMethod(Method method) {
            this.method = method;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Handler setUrl(String url) {
            this.url = url;
            return this;
        }
    }
}
