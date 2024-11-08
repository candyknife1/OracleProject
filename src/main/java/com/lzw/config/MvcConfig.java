package com.lzw.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Author: lzw
 * @Description: TODO
 * @Date: 2024/8/21 21:13
 * @Version: 1.0
 */
@Configuration
@ComponentScan("com.lzw")
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        //registry可以快速添加前后缀
//        registry.jsp("/WEB-INF/views/", ".jsp");
//        //handler -> index
//    }

    //开启静态资源查找
    //handlermapper先找有没有相应的handler,没有的话找静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    //添加拦截器
//    public void addInterceptors(InterceptorRegistry registry) {
//        //方案1:拦截全部请求
//        //registry.addInterceptor(new MyInterceptor());
//
//        //方案2:指定地址拦截
//        //* 任意一层字符串  ** 任意多层字符串
//        registry.addInterceptor(new MyInterceptor())
//                .addPathPatterns("/stu/**");
//
//                //排除某些路径
//        //.excludePathPatterns("/stu/add")
//                //.excludePathPatterns("/stu/update")
//                //.excludePathPatterns("/stu/delete")
//
//
//    }
}
