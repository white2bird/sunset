package com.sunset.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebConfig implements  WebMvcConfigurer {
    public  void addInterceptors(InterceptorRegistry interceptorRegistry) {
        // 拦截器
        interceptorRegistry.addInterceptor(new InterceptorConfig()).excludePathPatterns("/static/**");

    }
    public void addResourceHandlers(ResourceHandlerRegistry r){
        r.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        // 静态资源映射，解决上传图片后无法访问
        r.addResourceHandler("/images/**").addResourceLocations("file:" + System.getProperty("user.dir") + "/upload/images/");
        r.addResourceHandler("/avator/**").addResourceLocations("file:" + System.getProperty("user.dir") + "/upload/avator/");
    }

}
