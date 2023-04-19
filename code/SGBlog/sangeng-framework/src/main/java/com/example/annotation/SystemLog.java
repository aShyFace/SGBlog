package com.example.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;




/**
 * 自定义日志注解
 * @author Zhi
 * @since 2023-04-19 11:20:28
 */
// 这两个都是元注解——这些注解只提供了一些基本功能，具体实现由代码控制。我们可以通过它来实现自定义注解
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SystemLog {



}
