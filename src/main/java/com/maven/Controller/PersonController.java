package com.maven.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maven.Bean.Person;
import com.maven.Mapper.PersonMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/person")
public class PersonController {
    @Resource(name = "personMapper")
    private PersonMapper personMapper;

    //存储预返回页面的数据对象
    private Map<String,Object> result=new HashMap<>();

    @PostMapping("/all")
    public Object GetPerson(){
        PageHelper.startPage(1,1);
        List<Person> person=personMapper.getAllPerson();
        PageInfo<Person> pageInfo=new PageInfo<>(person,3);
        List<Person> personList=pageInfo.getList();
        result.put("personlist",personList);
        System.out.println("执行结束");
        return  result;
    }
}
