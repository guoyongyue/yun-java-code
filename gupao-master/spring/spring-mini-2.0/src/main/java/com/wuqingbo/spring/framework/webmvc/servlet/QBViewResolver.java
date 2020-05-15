package com.wuqingbo.spring.framework.webmvc.servlet;

import java.io.File;
import java.util.Locale;

/**
 * Created by qingbowu.
 */
public class QBViewResolver {

    private static final String DEFAULT_TEMPLATE_SUFFX = ".html";

    private File templateRootDir;
    private String viewName;

    public QBViewResolver(String template) {
        String templatePath = this.getClass().getClassLoader().getResource(template).getFile();
        this.templateRootDir = new File(templatePath);
    }

    public QBView resolveViewName(String viewName, Locale locale) throws Exception {
        if (null == viewName || "".equals(viewName)){return null;}
        viewName = viewName.endsWith(DEFAULT_TEMPLATE_SUFFX) ? viewName : (viewName+DEFAULT_TEMPLATE_SUFFX);
        File file = new File((templateRootDir.getPath() + "/" + viewName).replaceAll("/+", "/"));
        return new QBView(file);
    }
}
