package com.maven.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maven.Bean.Person;
import com.maven.Mapper.PersonMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Resource(name = "personMapper")
    private PersonMapper personMapper;

    //存储预返回页面的数据对象
    private Map<String,Object> result=new HashMap<>();

    @RequestMapping("/first")
    public String test(ModelMap map) {
        map.put("thText", "设置文本内容");
        map.put("thUText", "设置文本内容");
        map.put("thValue", "设置当前元素的value值");
        map.put("thEach", Arrays.asList("列表", "遍历列表"));
        map.put("thIf", "msg is not null");

        return "html/test";
    }

    @ResponseBody
    @PostMapping("/two")
    public Object getPerson(@RequestBody String msg){
        System.out.println(msg);
        PageHelper.startPage(1,1);
        List<Person> person=personMapper.getAllPerson();
        PageInfo<Person> pageInfo=new PageInfo<>(person,3);
        Person person2=pageInfo.getList().get(0);
        result.put("person",person2);
        System.out.println("返回数据");
        return result;
    }

    @RequestMapping("/user")
    public String touserlist(){
        return "html/userlist";
    }

    @RequestMapping("/register")
    public String toregister(){
        return "templates/system/register";
    }

}
