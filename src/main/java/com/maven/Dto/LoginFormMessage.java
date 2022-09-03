package com.maven.Dto;

import org.springframework.stereotype.Component;

/**
 * @author 谢秉均
 * @description 登录表单提交数据
 * @date 2022/9/1--14:18
 */
@Component
public class LoginFormMessage {

    private String  jobNumber;//工号

    private String  name;//姓名

    private String  password;//密码

    private Integer usertype;//账号类型

    private String verifiCode;//验证码

    public LoginFormMessage() {
    }

    public LoginFormMessage(String jobNumber, String name, String password, Integer usertype, String verifiCode) {
        this.jobNumber = jobNumber;
        this.name = name;
        this.password = password;
        this.usertype = usertype;
        this.verifiCode = verifiCode;
    }

    @Override
    public String toString() {
        return "LoginFormMessage{" +
                "jobNumber='" + jobNumber + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", usertype=" + usertype +
                ", verifiCode='" + verifiCode + '\'' +
                '}';
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public String getVerifiCode() {
        return verifiCode;
    }

    public void setVerifiCode(String verifiCode) {
        this.verifiCode = verifiCode;
    }
}
