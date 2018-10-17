package com.hadwin.signatureverification;

import com.hadwin.signatureverification.config.InterceptorConfig;
import com.hadwin.signatureverification.interceptor.TestIntercepter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


//@SpringBootApplication(scanBasePackages = {"com.hadwin.signatureverification.controller", "com.hadwin.signatureverification.interceptor"})
@SpringBootApplication
public class HelloworldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloworldApplication.class, args);
    }
    @Bean
    InterceptorConfig getInterceptorConfig(){
        return new InterceptorConfig();
    }
    @Bean
    TestIntercepter getTestIntercepter(){
        return new TestIntercepter();
    }
}
