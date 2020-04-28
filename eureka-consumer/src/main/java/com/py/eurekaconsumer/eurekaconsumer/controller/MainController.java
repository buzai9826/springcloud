package com.py.eurekaconsumer.eurekaconsumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MainController {
    /**
     * 抽象接口 相当于定义了一个标准
     */
    @Autowired
    DiscoveryClient discoveryClient;


    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    LoadBalancerClient lb;

    @GetMapping("/getHi")
    public String getHi() {
        return "Hi";
    }

    @GetMapping("/client")
    public String client() {
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            System.out.println(service);
        }
        return "Hi";
    }

    @GetMapping("/getInstance")
    public Object getInstance() {
        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        return provider;
    }

    @GetMapping("/getInstance2")
    public Object getInstance2() {
        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        for (ServiceInstance serviceInstance : provider) {
            System.out.println(ToStringBuilder.reflectionToString(serviceInstance));
        }
        return provider;
    }

    @GetMapping("/getInstance3")
    public Object getInstance3() {
        //        获取具体的服务
//        List<InstanceInfo> infoList = eurekaClient.getInstancesById("py9826:provider:80");
//       使用服务名，找服务列表
        List<InstanceInfo> infoList = eurekaClient.getInstancesByVipAddress("provider", false);
        for (InstanceInfo ins : infoList) {
            System.out.println(ins);
        }
        if (infoList.size() > 0) {
            InstanceInfo instanceInfo = infoList.get(0);
            if (instanceInfo.getStatus() == InstanceInfo.InstanceStatus.UP) {
                String url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/getHi";
//                System.out.println(url);
                RestTemplate restTemplate = new RestTemplate();
                String respstr = restTemplate.getForObject(url, String.class);
                System.out.println("respstr----" + respstr);
            }
        }
        return "a";
    }

    @GetMapping("/getInstance4")
    public Object getInstance4() {
//        ribbon 完成客户端的负载均衡 过滤掉了down的机器  choose
        ServiceInstance provider = lb.choose("provider");
        String url = "http://" + provider.getHost() + ":" + provider.getPort() + "/getHi";
//                System.out.println(url);
        RestTemplate restTemplate = new RestTemplate();
        String respstr = restTemplate.getForObject(url, String.class);
        System.out.println("返回结果："+respstr);
        return "";
    }
}
