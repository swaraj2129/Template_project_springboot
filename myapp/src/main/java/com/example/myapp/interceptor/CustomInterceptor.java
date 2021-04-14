package com.example.myapp.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CustomInterceptor implements HandlerInterceptor {
	  
	private static final Logger log = LoggerFactory.getLogger(CustomInterceptor.class);

	@Override
	   public boolean preHandle(
	      HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		   System.out.println("Pre Handle method is Calling");
		   log.info("[preHandle][" + request + "]" + "[" + request.getMethod()
		      + "]" + request.getRequestURI() + "\n" + getParameters(request));
		    
	      return false;
	   }
	   @Override
	   public void postHandle(
	      HttpServletRequest request, HttpServletResponse response, Object handler, 
	      ModelAndView modelAndView) throws Exception {
	        log.info("[postHandle][" + request + "]");

		      System.out.println("Post Handle method is Calling");

	   }
	   
	   @Override
	   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, 
	      Object handler, Exception exception) throws Exception {
		      System.out.println("Request and Response is completed");
		        log.info("[afterCompletion][" + request + "][exception: " + exception + "]");


	   }
	   
	   private String getParameters(final HttpServletRequest request) {
	        final StringBuffer posted = new StringBuffer();
	        final Enumeration<?> e = request.getParameterNames();
	        if (e != null)
	            posted.append("?");
	        while (e != null && e.hasMoreElements()) {
	            if (posted.length() > 1)
	                posted.append("&");
	            final String curr = (String) e.nextElement();
	            posted.append(curr).append("=");
	            if (curr.contains("password") || curr.contains("answer") || curr.contains("pwd")) {
	                posted.append("*****");
	            } else {
	                posted.append(request.getParameter(curr));
	            }
	        }

	        final String ip = request.getHeader("X-FORWARDED-FOR");
	        final String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
	        if (!Strings.isNullOrEmpty(ipAddr))
	            posted.append("&_psip=" + ipAddr);
	        return posted.toString();
	    }

	    private String getRemoteAddr(final HttpServletRequest request) {
	        final String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
	        if (ipFromHeader != null && ipFromHeader.length() > 0) {
	            log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
	            return ipFromHeader;
	        }
	        return request.getRemoteAddr();
	    }
	}