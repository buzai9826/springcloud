package com.py.userprovider.userprovider.controller;

import com.py.userapi.userapi.UserApi;
import com.py.userapi.userapi.entity.Person;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class UserController implements UserApi {

    @Value("${server.port}")
    String port;

    private AtomicInteger count = new AtomicInteger();

//    @Override
//    public String alive() {
//        System.out.println("进这个");
//        return "port:" + port;
//    }


    @Override
    public String getbyId(String id) {
        return id;
    }

    @Override
    public String alive() {
//        try {
//            System.out.println("准备睡");
//
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        int i = count.getAndIncrement();
        System.out.println("provider 端口"+port+"====好的第：" + 0 + "次调用");
        return "port:" + port;
    }


    @GetMapping("/getMap")
    public Map<Integer, String> getMap(@RequestParam("id") Integer id) {
        // TODO Auto-generated method stub
        System.out.println(id);
        return Collections.singletonMap(id, "mmeme");
    }

    @GetMapping("/getMap2")
    public Map<Integer, String> getMap2(Integer id, String name) {
        // TODO Auto-generated method stub
        System.out.println(id);
        return Collections.singletonMap(id, name);
    }

    @GetMapping("/getMap3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }


    @PostMapping("/postMap")
    public Map<Integer, String> postMap(@RequestBody Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }

    //    @PostMapping("/postPeron")
    public Person postPeron(@RequestBody Person person) {
        // TODO Auto-generated method stub
        System.out.println(ToStringBuilder.reflectionToString(person));
        return person;
    }
}
