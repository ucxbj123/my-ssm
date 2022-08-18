package com.maven.Config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.maven.Proxy"})
//开启生成代理对象，代替xml文件实现全注解开发
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AopConfig {
}
