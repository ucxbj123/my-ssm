package com.maven.Service.Impl;

import com.maven.Bean.User;
import com.maven.Dto.LoginFormMessage;
import com.maven.Mapper.UserMapper;
import com.maven.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl  {

    @Autowired
    private UserMapper userMapper;

    /**
    *@description：添加用户
    *@Param:
    *@return:
    *@Author: 谢秉均
    *@date: 2022/9/1--14:55
    */
    public int AddUser(User user){
        int result=userMapper.insert(user);
        return result;
    }

    /**
    *@description：根据工号查询个人信息
    *@Param:
    *@return:
    *@Author: 谢秉均
    *@date: 2022/9/1--14:54
    */
    public User selectByJobnumber(String jobnumber){
        return userMapper.selectByJobnumber(jobnumber);
    }

    /**
    *@description：通过工号、姓名、密码校验是否正确（用户登录）
    *@Param:
    *@return:
    *@Author: 谢秉均
    *@date: 2022/9/1--14:58
    */
    public boolean selectByJobAndPasswordService(LoginFormMessage message){
        boolean result = true;
        List<User> user = userMapper.selectByJobAndPassword(message.getJobNumber(), message.getPassword());
        if(user == null || user.size() >1){     //判断是否存在该用户
            result=false;
        }else if(user.get(0).getIssuperadmin() != message.getUsertype()){
            result=false;//账号类型不对
        }
        return result;
    }

}
