package com.py.userconsumer.userconsumer.controller;


import com.py.userapi.userapi.entity.Person;
import com.py.userconsumer.userconsumer.service.RestService;
import com.py.userconsumer.userconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class consumerController {

    @Autowired
    UserService userService;

    @Autowired
    RestService restService;

    @Value("${server.port}")
    String port;

   // @Autowired
//    MashibingApi mapi;


     @RequestMapping("/alive")
    public String alive() {
       return userService.alive() + "aaaa";
    }

    @GetMapping("/alive2")
    public String alive2() {
        return "consumer"+port+"调起------>"+restService.alive() ;
    }

    @RequestMapping("/getbyid")
    public String getbyid(String id) {
        return userService.getbyId("11");
    }


//    @GetMapping("/vip")
//    public String vip() {
//
//        return mapi.getVip();
//    }

    @GetMapping("/map")
    public Map<Integer, String> map(Integer id) {
        System.out.println(id);
        return userService.getMap(id);
    }

    @GetMapping("/map2")
    public Map<Integer, String> map2(Integer id, String name) {
        System.out.println(id);
        return userService.getMap2(id, name);
    }


    @GetMapping("/map3")
    public Map<Integer, String> map3(@RequestParam Map<String, Object> map) {
//		System.out.println(id);
        HashMap<String, Object> map1 = new HashMap<>(2);
//
        map1.put("id", "1");
        map1.put("name", "哈哈");
        System.out.println(map1);
        return userService.getMap3(map1);
    }


    @GetMapping("/map4")
    public Map<Integer, String> map4(@RequestParam Map<String, Object> map) {
//		System.out.println(id);
//		HashMap<String, Object> map = new HashMap<>(2);
//
//		map.put("id", id);
//		map.put("name", name);
//		syso
        System.out.println(map);
        return userService.postMap(map);
    }

    @GetMapping("/postPeron")
    public Person postPerson(@RequestParam Map<String, String> map) {
        Person person = new Person(Integer.parseInt(map.get("id").toString()), map.get("name"));
        return userService.postPeron(person);
    }
}
