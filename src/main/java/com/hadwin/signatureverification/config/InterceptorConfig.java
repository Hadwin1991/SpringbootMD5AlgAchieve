package com.hadwin.signatureverification.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.*;
import com.hadwin.signatureverification.interceptor.TestIntercepter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Bean
    public TestIntercepter userInterceptor() {
        return new TestIntercepter();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInterceptor()).addPathPatterns("/hello");
        super.addInterceptors(registry);
    }
}
