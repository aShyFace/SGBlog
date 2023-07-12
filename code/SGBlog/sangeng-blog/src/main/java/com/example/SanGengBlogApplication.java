package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableScheduling
@SpringBootApplication
@MapperScan("com.example.mapper")
public class SanGengBlogApplication {
    public static void main(String[] args) {
       //这里的TestApplication 是当前工程的启动类名
        SpringApplication springApplication = new SpringApplication(SanGengBlogApplication.class);
        //关闭启动logo和启动日志的输出
        springApplication.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext run = springApplication.run(args);

//        List<Integer> a = testGrammar();
//         qiniu();
//         System.out.println("程序结束了");
    }

//    public static List testGrammar(){
////        Arrays是一个工具类，封装了通用的数组操作（搜索，排序，复制，比较）
//        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
//        int len = list.size();
//        Iterator<Integer> list_iter = list.iterator();
//
//        return list;
//    }



}
