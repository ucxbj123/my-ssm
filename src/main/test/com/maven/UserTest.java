package com.maven;


import com.maven.Mapper.PersonMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Test
    public void testLocalDateTime(){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss");
        String msg=LocalDateTime.now().format(formatter);
        System.out.println("msg:"+msg);
        LocalDateTime dateTime = LocalDateTime.parse(msg,formatter);
        System.out.println("datetime:"+dateTime);

    }
}
