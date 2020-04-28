package com.py.userconsumer.userconsumer.service;

import com.py.userapi.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * FeignClient做资源地址的拼接
 */
//不走Eureka的直接调用
//@FeignClient(name = "aaa",url = "http://localhost:81/")
//    通过Eureka 找到服务
//    fallback 降级处理的方式
//@FeignClient(name = "user-provider",fallback = UserProviderBack.class)
@FeignClient(name = "user-provider",fallbackFactory = UserProviderFactory.class)
//@FeignClient(value = "user-provider")
public interface UserService extends UserApi {

    /**
     * 自定义方法 不是api定义的
     * @GetMapping("/getMap") 是给FeignClient拼接服务地址用的
     * @param id @RequestParam("id")也是给feign看的
     * @return
     */
    @GetMapping("/getMap")
    Map<Integer, String> getMap(@RequestParam("id") Integer id);

    @GetMapping("/getMap2")
    Map<Integer, String> getMap2(@RequestParam("id") Integer id,@RequestParam("name") String name);

    @GetMapping("/getMap3")
    Map<Integer, String> getMap3(@RequestParam Map<String, Object> map);

    @PostMapping("/postMap")
    Map<Integer, String> postMap(Map<String, Object> map);
}
