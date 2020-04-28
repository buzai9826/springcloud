package com.py.userapi.userapi;

import com.py.userapi.userapi.entity.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//开启这个注解启动项目的时候会报错
//@RequestMapping("/User")
public interface UserApi {

    @RequestMapping("/aliv")
    public String alive();

    @RequestMapping("/getbyId")
    public String getbyId(@RequestParam("id") String id);

    @PostMapping("/postPeron")
    public Person postPeron(@RequestBody Person  person);
}
