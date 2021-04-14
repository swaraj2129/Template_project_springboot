package com.example.myapp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomInterceptorConfig implements WebMvcConfigurer {

  @Autowired 
  CustomInterceptor yourInjectedInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    
    registry.addInterceptor(yourInjectedInterceptor);
   
  }
}