package com.maven.Service;

import com.maven.Bean.User;
import com.maven.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int AddUser(User user){
        int result=userMapper.insert(user);
        return result;
    }

    public User selectByJobnumber(String jobnumber){
        User user=userMapper.selectByJobnumber(jobnumber);
        return user;
    }

}
