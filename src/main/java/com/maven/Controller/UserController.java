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
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //存储预返回页面的数据对象
    private Map<String,Object> result=new HashMap<>();

    /**
     * 新增普通用户
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "/adduser")
    public Object AddUser(@RequestBody String users) throws ParseException {
        User user1= JSONObject.parseObject(users,User.class);
        User user=userService.selectByJobnumber(user1.getJobNumber());
        if(user.getId()!=user1.getId()){
            String format = LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")
            );  // 取当前时间并将datetime转字符串
            LocalDateTime dateTime = LocalDateTime.parse(
                    format,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")
            );  // 字符串转datetime;
            user1.setCreateusertime(dateTime);
            userService.AddUser(user1);
            result.put("status","success");
            return result;
        }else{
            result.put("status","error，已存在该用户");
            return result;
        }
    }
}
