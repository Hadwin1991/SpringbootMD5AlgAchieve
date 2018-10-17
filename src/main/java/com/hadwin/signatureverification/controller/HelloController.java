package com.hadwin.signatureverification.controller;

import com.hadwin.signatureverification.utils.ParamSignUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @RestController 相当于@Controller和@RequestBody
 *
 */
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.hadwin.signatureverification.interceptor"})
public class HelloController {
    @RequestMapping(value={"/hello"},params={"key=key","secret=test","sign=AA18FAB221D27AEA9A22F7D6DBFA525A","username=hadwin","password=04271510","timeStamp=1539706004589"})
    public String hello(){
        System.out.println("--- user home ---");

        return "Hello Word SpringBoot";
    }

    @RequestMapping("/testMD5")
    public String testmd5() {
        HashMap<String, String> signMap = new HashMap<String, String>();
        signMap.put("username", "01");
        signMap.put("password", "02");
        signMap.put("secret", "03");
        String secret = "test";
        String SignHashMap = ParamSignUtils.sign(signMap, secret);
        System.out.println("SignHashMap:" + SignHashMap);
        //List<String> ignoreParamNames = new ArrayList<String>();
        //ignoreParamNames.add("a");
        //HashMap SignHashMap2 = ParamSignUtils.sign(signMap, ignoreParamNames, secret);
        //System.out.println("SignHashMap2:" + SignHashMap2);
        return ("SignHashMap2:" + SignHashMap);
    }
}
