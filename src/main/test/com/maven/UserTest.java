package com.maven;


import com.alibaba.fastjson.JSONObject;
import com.maven.Bean.User;
import com.maven.Mapper.PersonMapper;
import com.maven.Service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//Spring5整合Junit4
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-config/applicationContext.xml")
public class UserTest {

    @Autowired
    private PersonMapper personMapper;

    @Test
    public void SelectAllPerson(){
        System.out.println(personMapper.getAllPerson());
    }
}
