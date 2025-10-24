//package com.security.config;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
////@Order(1)
//public class MySecondFilter implements Filter {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        System.out.println("LOG do MySecondFilter doFilter()");
//
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//
//        if (request.getMethod().equals("GET")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            ((HttpServletResponse) servletResponse).setStatus(500);
//            servletResponse.getOutputStream().write("Error in Spring, the method is not GET".getBytes());
//        }
//    }
//
//}
