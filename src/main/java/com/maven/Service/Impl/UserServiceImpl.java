package com.maven.Service.Impl;

import com.maven.Bean.User;
import com.maven.Mapper.UserMapper;
import com.maven.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl  {

    @Autowired
    private UserMapper userMapper;

    public int AddUser(User user){
        int result=userMapper.insert(user);
        return result;
    }

    public User selectByJobnumber(String jobnumber){
        return userMapper.selectByJobnumber(jobnumber);
    }

}
