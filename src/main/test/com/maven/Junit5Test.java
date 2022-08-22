package com.maven;

import com.maven.Mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * Spring5整合junit5
 * @SpringJUnitConfig是一个复合注解，效果等于@ExtendWith、@ContextConfiguration
 */
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration("classpath:spring-config/applicationContext.xml")
@SpringJUnitConfig(locations = "classpath:spring-config/applicationContext.xml")
public class Junit5Test {
    @Autowired
    private PersonMapper personMapper;

    @Test
    public void SelectAllPerson(){
        System.out.println(personMapper.getAllPerson());
    }

}
