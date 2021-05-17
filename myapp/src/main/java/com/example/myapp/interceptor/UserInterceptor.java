package com.example.myapp.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class UserInterceptor implements HandlerInterceptor {

	/**
	 *
	 * @param request from the url where Interceptor is used
	 * @param response after the Intercepor
	 * @param handler
	 * @return a boolean value indicated if the url properties are correct
	 * @throws Exception
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag = true;
		String method = request.getMethod();
		int contentLength = request.getContentLength();
		if (method.equalsIgnoreCase("post") || method.equalsIgnoreCase("put")) {
			String contentType = request.getContentType();
			if (contentType != null && !contentType.equalsIgnoreCase("application/json")) {
				flag = false;
			}
			else if (contentLength <= 2) {
				flag = false;
			}
		}
		if (!flag) {
			response.sendRedirect("/invalid");
		}
		return flag;

		// return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
