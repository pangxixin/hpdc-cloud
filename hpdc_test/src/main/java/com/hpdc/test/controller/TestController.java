package com.hpdc.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin
@RefreshScope
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${ip}")
    public String ip;

    @RequestMapping(value = "/hello/{username}", method = RequestMethod.GET)
    public String Test1(@PathVariable String username) {
        System.out.println("从远程配置中获取的变量ip: " + ip);
        return  "Hello, " + username + " wellcome to HRB";
    }

    @RequestMapping(value = "/hello/{username}", method = RequestMethod.GET)
    public String Test2(@PathVariable String username) {

        System.out.println("从远程配置中获取的变量ip: " + ip);
        return  "Hello, " + username + " wellcome to HRB";
    }


}
