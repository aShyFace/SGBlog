package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.ResponseResult;
import com.example.domain.entity.User;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户表(User)表服务接口
 *
 * @author Zhi
 * @since 2023-03-31 13:56:41
 */
public interface LoginService extends IService<User> {

    HashMap login(User user);

    void logout(Long userId);
}

