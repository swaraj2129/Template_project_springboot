package com.example.myapp.config;

import com.example.myapp.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtAuthFilterConfig {

	/**
	 *
	 * @return the FilterRegistrationBean instance for JwtAuthenticationFilter
	 */
	@Autowired
	JwtAuthenticationFilter jwtAuthenticationFilter;
	@Bean
	public FilterRegistrationBean<JwtAuthenticationFilter> filterRegistrationBean(){
		   FilterRegistrationBean < JwtAuthenticationFilter > registrationBean = new FilterRegistrationBean();
		  //JwtAuthenticationFilter customURLFilter = new JwtAuthenticationFilter();
		  registrationBean.setFilter(jwtAuthenticationFilter);
		  registrationBean.addUrlPatterns("/api/v1/user/*");
		  return registrationBean;
	}

	// was not able to put more than one FilterRegistrationBean in one class
}
