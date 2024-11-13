package com.lzw.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMVCInlt extends AbstractAnnotationConfigDispatcherServletInitializer {

    // 根应用上下文配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // 有应用程序上下文的配置类
        return new Class[]{}; // 暂时没有根配置类
    }

    // Spring MVC 配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfig.class};
    }

    // DispatcherServlet 的映射路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
