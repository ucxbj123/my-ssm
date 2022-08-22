package com.maven.Controller;


import com.alibaba.fastjson.JSONObject;
import com.maven.Bean.User;
import com.maven.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 新增普通用户
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "/adduser")
    public void AddUser(@RequestBody String user) throws ParseException {
        User user1= JSONObject.parseObject(user,User.class);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
        String data2=dateFormat.format(date);
        user1.setCreateusertime(data2);
        System.out.println(data2);
        userService.AddUser(user1);
    }
}
