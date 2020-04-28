package com.py.userconsumer.userconsumer.service;

import com.py.userapi.userapi.entity.Person;
import feign.hystrix.FallbackFactory;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserProviderFactory  implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
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
                System.out.println(throwable);
                System.out.println(ToStringBuilder.reflectionToString(throwable));
                throwable.printStackTrace();//打印栈信息
                if(throwable instanceof RuntimeException){
                    return "服务已下线"+throwable.getLocalizedMessage();
                }
                return "通过Factory降级";
            }

            @Override
            public String getbyId(String id) {
                return null;
            }

            @Override
            public Person postPeron(Person person) {
                return null;
            }
        };
    }
}
