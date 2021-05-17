package com.example.myapp.util;


import org.springframework.stereotype.Service;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Service
public class NotAuthorizedUtil {

    public void afterWrongAuth(ServletResponse response) throws IOException {
        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // get the output stream from response
            PrintWriter out = response.getWriter();
            // fill in the response headers
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            // write content to output stream.
            out.print("Username or Password is wrong");
            out.flush();
        }

        //return new NotAuthorizedUtil();
    }
}

