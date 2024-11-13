package com.lzw.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.lzw")
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {

    // 启用静态资源查找
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 配置静态资源访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 允许直接访问根目录下的 JSP 文件
        registry.addResourceHandler("/**").addResourceLocations("/");
    }
}
