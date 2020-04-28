package com.py.eurekaconsumer.eurekaconsumer.controller;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class LoadBalancingController {

    @Autowired
    LoadBalancerClient lb;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    AtomicInteger atomicInteger;

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/client6")
    public Object client6() {
        //        ribbon 完成客户端的负载均衡 过滤掉了down的机器  choose
        ServiceInstance provider = lb.choose("provider");
        String url = "http://" + provider.getHost() + ":" + provider.getPort() + "/getHi";
        RestTemplate restTemplate = new RestTemplate();
        String respstr = restTemplate.getForObject(url, String.class);
        System.out.println("返回结果：" + respstr);
        return respstr;
    }

    /**
     * 手动实现负载均衡
     */
    @GetMapping("/client7")
    public Object client7() {
        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        //随机算法
        int i = new Random().nextInt(provider.size());
        //轮询
        int andIncrement = atomicInteger.getAndIncrement();
        int i1 = andIncrement % provider.size() ;
        //权重
        for (ServiceInstance instance : provider) {
            instance.getMetadata();//在元数据里设置权重，拿到权重值之后再随机，权重越大的命中可能性越高

        }
        //发送请求
        ServiceInstance serviceInstance = provider.get(i1);
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/getHi";
        String respstr = restTemplate.getForObject(url, String.class);
        return respstr;
    }

    /**
     * restTemplate自动处理url
     * @return
     */
    @GetMapping("/client9")
    public Object client9() {
        ServiceInstance provider = lb.choose("provider");
        String url = "http://provider/getHi";
        String respstr = restTemplate.getForObject(url, String.class);
        System.out.println("返回结果：" + respstr);
        return respstr;
    }
}
