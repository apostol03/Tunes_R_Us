package com.yankovltd.tunes.config;

import com.yankovltd.tunes.web.interceptor.DeleteUserInterceptor;
import com.yankovltd.tunes.web.interceptor.StatusPanelInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final StatusPanelInterceptor statusPanelInterceptor;
    private final DeleteUserInterceptor deleteUserInterceptor;

    public WebConfiguration(StatusPanelInterceptor statusPanelInterceptor, DeleteUserInterceptor deleteUserInterceptor) {
        this.statusPanelInterceptor = statusPanelInterceptor;
        this.deleteUserInterceptor = deleteUserInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statusPanelInterceptor).addPathPatterns("/status-panel");
        registry.addInterceptor(deleteUserInterceptor).addPathPatterns("/delete/*");
    }
}

