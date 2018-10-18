package com.hadwin.signatureverification.controller;

import com.hadwin.signatureverification.utils.ParamSignUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @RestController 相当于@Controller和@RequestBody
 *
 */
@RestController
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.hadwin.signatureverification.interceptor"})
public class HelloController {
    //@RequestMapping(value={"/hello"},params={"key=key","secret=test","sign=2F42D92F00DE676AABBF2A259B02A126","username=hadwin","password=04271510","timeStamp=1539706004589"})
    //a2V5PWtleSZzZWNyZXQ9dGVzdCZzaWduPUFBMThGQUIyMjFEMjdBRUE5QTIyRjdENkRCRkE1MjVBJnVzZXJuYW1lPWhhZHdpbiZwYXNzd29yZD0wNDI3MTUxMCZ0aW1lU3RhbXA9MTUzOTcwNjAwNDU4OQ==
    @RequestMapping(value={"/hello"},params={"RSAvalue=3ebf462b59f425b2fb6041aea2a86e308f3f25a1c9b981956df9e50b6f8a83645e81a0b8a7bcddcf40126dd8e115227b9ff5f713131f865ae9cb32215de6225381437191d6f20f3cf731cc474512be1886ba3f922ff143947db613f9c0c198b0d1a7a46f82401a7b913574daca66af6be40cb8d246458bfdc2f218d06aab08741f4e411a8f4a6bfe83cf731084472b034670054c56a0118e6e2f77386b0174cf696669bad50af898451ed86e39faa3edfa3aca54698e863d2d1b8ff3758e30b18a5a7ce0e7f9f80d6ddf3c687eb052b73adce0182086d77db575a439c486eccf9da75344b7257e05be8ca815f2ba235d506a3682e52ccfb7cc697499f252ca24"})
    public String hello(){
        System.out.println("--- user home ---");

        return "Hello Word SpringBoot";
    }

}
