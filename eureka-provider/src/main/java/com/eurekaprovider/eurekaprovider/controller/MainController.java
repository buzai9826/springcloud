package com.eurekaprovider.eurekaprovider.controller;

import com.eurekaprovider.eurekaprovider.entity.Person;
import com.eurekaprovider.eurekaprovider.service.HealthStautsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Map;

@RestController
public class MainController {
    @Autowired
    HealthStautsService healthStatusSrv;

    @Value("${server.port}")
    String port;

    @GetMapping("/getHi")
    public String getHi() {
        return "Hi-----" + port;
    }

    @GetMapping("/getMap")
    public Map<String, String> getMap() {
        return Collections.singletonMap("id", "100");
    }
    @GetMapping("/getObj")
    public Person getObj() {
        Person p = new Person(1, "哈哈");
        return p;
    }
    @GetMapping("/getObj2")
    public Person getObj2(@RequestBody String name) {
        Person p = new Person(1, name);
        return p;
    }

    @GetMapping("/health")
    public String health(@RequestParam("status") Boolean status) {

        healthStatusSrv.setStatus(status);
        return healthStatusSrv.getStatus();
    }

    @PostMapping("/postParam")
    public URI postParam(@RequestBody Person person, HttpServletResponse response) throws URISyntaxException {
        URI location =new URI("http://www.baidu.com/s?wd="+person.getName().trim());
        response.addHeader("Location",location.toString());
        return location;
    }
}
