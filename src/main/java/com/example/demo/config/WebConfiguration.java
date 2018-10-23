package com.example.demo.config;

import com.example.demo.filter.CustomFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public FilterRegistrationBean testFilterRegistration(){


        FilterRegistrationBean registrationBean=new FilterRegistrationBean();
        registrationBean.setFilter(new CustomFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("customFilter");
        registrationBean.setOrder(6);


        return registrationBean;
    }
}
