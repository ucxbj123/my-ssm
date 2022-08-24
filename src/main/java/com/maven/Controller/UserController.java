package com.maven.Controller;


import com.maven.Bean.User;
import com.maven.Service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //存储预返回页面的数据对象
    private Map<String,Object> result=new HashMap<>();

    /**
     * 新增普通用户
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST,value = "/adduser")
    public Object AddUser(@RequestBody(required = false) User users) {
        System.out.println(users);
        if(users==null || users.getJobNumber()==null){
            result.put("status","用户信息为空");
            return result;
        }else {
            User user= userServiceImpl.selectByJobnumber(users.getJobNumber());
            //若查询返回为0条数据，则对象为null
            if(user==null){
                try {
                    //如果新添加用户没有对账号类型进行选择，默认不是超管
                    if(users.getIssuperadmin()==null){
                        users.setIssuperadmin(0);
                    }
                    //新建用户是未删除状态
                    users.setIsdeleted(0);
                    String format = LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")
                    );  // 取当前时间并将datetime转字符串
                    LocalDateTime dateTime = LocalDateTime.parse(
                            format,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm:ss")
                    );  // 字符串转datetime;
                    users.setCreateusertime(dateTime);
                    userServiceImpl.AddUser(users);
                    result.put("status","success");
                    return result;
                }catch (Exception e){
                    result.put("status","error，添加失败");
                    return result;
                }
            }else{
                result.put("status","error，已存在该用户");
                return result;
            }
        }
    }
}
