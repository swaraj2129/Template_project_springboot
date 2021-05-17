/*
package com.example.myapp.filter;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;

import com.example.myapp.service.AuthService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AuthFilter implements Filter {

    @Autowired
    AuthService authService;
	org.slf4j.Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

*/
/*
    @Override
    public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
           LOG.info(
          "Logging Request  {} : {}", req.getMethod(),
          req.getRequestURI());
        chain.doFilter(request, response);
        LOG.info(
          "Logging Response :{}",
          res.getContentType());
    }

*//*


    public static final String AUTHENTICATION_HEADER = "Authorization";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("inat");
        if (request instanceof HttpServletRequest) {
            final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            final String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

           // final AuthService authService = new AuthService();
            final boolean authenticationStatus = authService.authenticate(authCredentials);

            if (authenticationStatus) {
                chain.doFilter(request, response);
            } else {
                if (response instanceof HttpServletResponse) {
                    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
            }
        }
    }
*/
/*    public void init(FilterConfig filterConfig) throws ServletException {
        // No Implementation
    }*//*



    public void destroy() {
        // No Implementation
    }

}
*/
