package com.sunset.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Slf4j
public class WebConfig extends WebMvcConfigurationSupport {
    protected void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(new InterceptorConfig()).addPathPatterns("/**");
        super.addInterceptors(interceptorRegistry);

    }

}
