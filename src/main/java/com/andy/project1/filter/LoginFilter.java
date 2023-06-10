package com.andy.project1.filter;


import com.andy.project1.util.Constant;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("In LoginFilter");
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute(Constant.LOGIN_SESSION_KEY) != null) {
            filterChain.doFilter(request, response);
        } else {
            // redirect back to the login page if user is not logged in
            response.sendRedirect("/login");
        }
    }

    // Use to prevent infinite filtering if we request the "login" page
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        boolean result = "/login".equals(path)
                || "/register".equals(path)
                || "/contactus".equals(path);
        return result;
    }
}
