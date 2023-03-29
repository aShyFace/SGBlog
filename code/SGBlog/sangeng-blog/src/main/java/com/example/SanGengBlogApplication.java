package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@SpringBootApplication
@MapperScan("com.example.mapper")
public class SanGengBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SanGengBlogApplication.class, args);

//        List<Integer> a = testGrammar();
//        System.out.println(a);
    }

    public static List testGrammar(){
//        Arrays是一个工具类，封装了通用的数组操作（搜索，排序，复制，比较）
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        int len = list.size();
        Iterator<Integer> list_iter = list.iterator();

        return list;
    }

}