package com.py.eurekaconsumer.eurekaconsumer.controller;

import com.py.eurekaconsumer.eurekaconsumer.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class RestTemplateController {

    @Autowired
    LoadBalancerClient lb;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AtomicInteger atomicInteger;

    @Autowired
    DiscoveryClient discoveryClient;
    /**
     * restTemplate自动处理url
     * @return
     */
    @GetMapping("/client10")
    public Object client10() {
        ServiceInstance provider = lb.choose("provider");
        String url = "http://provider/getHi";
        //直接反转成对象
        String respstr = restTemplate.getForObject(url, String.class);
        //会带出一些请求的信息
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        System.out.println(forEntity);
        System.out.println("返回结果：" + respstr);
        return "aaa";
    }
    @GetMapping("/client11")
    public Object client11() {
        ServiceInstance provider = lb.choose("provider");
        String url = "http://provider/getMap";
        Map<String, String> forObject = restTemplate.getForObject(url, Map.class);
        return forObject;
    }
    @GetMapping("/client12")
    public Object client12() {
        ServiceInstance provider = lb.choose("provider");
        String url = "http://provider/getObj";
        Person p = restTemplate.getForObject(url, Person.class);
        return p;
    }


    @GetMapping("/client13")
    public Object client13() {
        //自动处理url
        String url = "http://provider/getObj2?name={1}";
        Person p = restTemplate.getForObject(url, Person.class,"大哥");
        return p;
    }

    @GetMapping("/client14")
    public Object client14() {
        String url = "http://provider/getObj2?name={name}";
        Map<String,String> map =Collections.singletonMap("name","嘻嘻");
        Person p = restTemplate.getForObject(url, Person.class,map);
        return p;
    }


    @GetMapping("/client15")
    public Object client15(HttpServletResponse response) throws Exception{
        String url ="http://provider/postParam";
        Map<String, String> map = Collections.singletonMap("name", " memeda");
        URI uri = restTemplate.postForLocation(url, map, Person.class);
        response.sendRedirect(uri.toURL().toString());
        return null;
    }



}
