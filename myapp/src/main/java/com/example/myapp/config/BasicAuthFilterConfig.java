package com.example.myapp.config;

import com.example.myapp.filter.BasicAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BasicAuthFilterConfig {

    /**
     *
     * @return the FilterRegistrationBean instance for BasicAuthFilter
     */
    @Autowired
    BasicAuthFilter basicAuthFilter;
    @Bean
    public FilterRegistrationBean<BasicAuthFilter> filterRegistrationBasicAuthBean(){
        FilterRegistrationBean < BasicAuthFilter > registrationBean = new FilterRegistrationBean();
     //   BasicAuthFilter customURLFilter = new BasicAuthFilter();
        registrationBean.setFilter(basicAuthFilter);
        registrationBean.addUrlPatterns("/register");
        return registrationBean;
    }

}
