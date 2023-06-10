package com.example.constant;

/**
 * @ClassName: RedisConstant
 * @Description: redis相关字面量
 * @author: Zhi
 * @date: 2023/4/4 下午3:01
 */
public class RedisConstant {
    /**
     * 普通用户信息存入redis时使用的key
     */
    public static final String USER_INFO_KEY = "bloglogin:";
    /**
     * 管理员信息存入reids时使用的key
     */
    public static final String ADMIN_INFO_KEY = "adminlogin:";
}
