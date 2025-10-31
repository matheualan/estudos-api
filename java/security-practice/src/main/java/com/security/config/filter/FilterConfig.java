//package com.security.config;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean<MyFilter> beanRegistration() {
//        FilterRegistrationBean<MyFilter> register = new FilterRegistrationBean<>();
//
//        register.setFilter(new MyFilter());
//        register.addUrlPatterns("/test/public");
//        register.addUrlPatterns("/h2-console/**");
//
//        return register;
//    }
//
//}
