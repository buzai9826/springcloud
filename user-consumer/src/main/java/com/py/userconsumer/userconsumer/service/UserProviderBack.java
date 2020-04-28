package com.py.userconsumer.userconsumer.service;

import com.py.userapi.userapi.entity.Person;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 降级处理 在服务调不通的时候返回兜底数据
 */
@Component
public class UserProviderBack  implements UserService {

    @Override
    public Map<Integer, String> getMap(Integer id) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap2(Integer id, String name) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap3(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<Integer, String> postMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public String alive() {
        return "已降级";
    }

    @Override
    public String getbyId(String id) {
        return null;
    }

    @Override
    public Person postPeron(Person person) {
        return null;
    }
}
