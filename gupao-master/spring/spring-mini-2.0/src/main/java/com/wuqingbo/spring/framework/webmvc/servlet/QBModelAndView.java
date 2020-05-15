package com.wuqingbo.spring.framework.webmvc.servlet;

import java.util.Map;

/**
 * Created by qingbowu.
 */
public class QBModelAndView {

    private String viewName;

    private Map<String,?> model;

    public QBModelAndView(String viewName) {
        this.viewName = viewName;
    }

    public QBModelAndView(String viewName, Map<String, ?> model) {
        this.viewName = viewName;
        this.model = model;
    }

    public String getViewName() {
        return viewName;
    }

    public Map<String, ?> getModel() {
        return model;
    }
}
