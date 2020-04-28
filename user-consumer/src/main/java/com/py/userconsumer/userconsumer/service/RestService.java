package com.py.userconsumer.userconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(defaultFallback = "back")//独立给每一个方法做降级处理
    public String alive() {
        String url = "http://user-provider/aliv";
        return restTemplate.getForObject(url,String.class);
    }

    public String back() {
        return "每个方法单独降级";
    }
}
