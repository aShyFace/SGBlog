package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.entity.User;
import com.example.domain.dto.UserAuthDto;
import com.example.domain.dto.UserInfoDto;

/**
 * 用户表(User)表服务接口
 *
 * @author Zhi
 * @since 2023-04-11 11:20:28
 */
public interface UserService extends IService<User> {

    User updateUserInfo(UserInfoDto userInfoDto);

    boolean register(UserAuthDto userAuthDto);
}

