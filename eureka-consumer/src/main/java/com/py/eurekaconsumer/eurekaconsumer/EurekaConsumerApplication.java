package com.py.eurekaconsumer.eurekaconsumer;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class EurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        return restTemplate;
    }

//    @Bean
//    public IRule myRule() {
//        //return new RoundRobinRule();
//        return new RandomRule();
////        return new RetryRule();
//    }

    @Bean
    AtomicInteger getAtomicInteger() {
        return new AtomicInteger();
    }
}
