package com.HarmyIndustries.Hitube.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("upload.path")
    String uploadPath;

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("HItubeV1demo\\img\\**")
                .addResourceLocations("file:\\" + "D:\\Harmy Industries\\HItube\\HItubeV1demo\\img\\");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}