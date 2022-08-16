package com.maven.Proxy;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PersonControllerProxy {
    @Before(value = "execution(* com.maven.Controller.PersonController.GetPerson(..))")
    public void before(){
        System.out.println("开始执行personcontroller。。。。。。。");
    }
}
