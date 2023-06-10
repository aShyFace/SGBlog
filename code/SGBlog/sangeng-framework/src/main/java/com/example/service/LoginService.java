package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.entity.User;
import com.example.domain.vo.user.LoginSucceseVo;

import java.util.HashMap;

/**
 * 用户表(User)表服务接口
 *
 * @author Zhi
 * @since 2023-03-31 13:56:41
 */
public interface LoginService extends IService<User> {

    LoginSucceseVo login(User user, String user_key);

    void logout(Long userId);
}

