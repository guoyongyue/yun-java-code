package com.yun.pattern.delegate.mvc;

import com.yun.pattern.delegate.mvc.controller.MemberController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @class name: DispatcherServlet <br/>
 * @description: 委派模式 <br/>
 * @date: 2020/5/23 19:36<br/>
 * @author: yun<br />
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


    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) {
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
            obj = handler.getMethod().invoke(handler.getController(), req.getParameter("mid"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
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