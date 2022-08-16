package com.maven.Controller;

import com.maven.Dao.Person;
import com.maven.Mapper.PersonMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
    @Resource(name = "personMapper")
    private PersonMapper personMapper;

    @PostMapping("/all")
    public Object GetPerson(){
        List<Person> person=personMapper.getAllPerson();
        String msg=person.toString();
        System.out.println("msg:"+msg);
        return  msg;
    }
}
