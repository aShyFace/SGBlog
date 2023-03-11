package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;


//告诉spring这是配置类，要加载这里的配置
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 要处理的请求地址，“**”表示匹配所有路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie（当然，前端发不发是另外一回事）
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }

//    private CorsConfiguration corsConfig(){
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.setAllowedMethods(new ArrayList<String>(Arrays.asList("GET", "POST", "DELETE", "PUT")));
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setMaxAge(3600L);
//        return corsConfiguration;
//    }
//
//    //有些方法返回的是 包含配置信息的对象。所以我们需要告诉spring，加载的时候获取这些方法的返回值
//    @Bean
//    public CorsFilter corsFilter(){
//        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        //两个参数。1.url的拦截规则；2. 改规则对应的跨域配置
//        corsConfigurationSource.registerCorsConfiguration("/**", corsConfig());
//        return new CorsFilter(corsConfigurationSource);
//    }


}


