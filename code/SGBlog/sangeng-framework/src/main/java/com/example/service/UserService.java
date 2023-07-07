package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.domain.dto.*;
import com.example.domain.entity.User;
import com.example.domain.vo.user.UserAdminInfoVo;
import com.example.domain.vo.user.UserPreviewVo;

/**
 * 用户表(User)表服务接口
 *
 * @author Zhi
 * @since 2023-04-11 11:20:28
 */
public interface UserService extends IService<User> {

    User updateUserInfo(UserInfoDto userInfoDto);

    boolean register(UserAuthDto userAuthDto);

  PageResult<UserPreviewVo> getUserList(PageParams pageParams, UserPageDto userPageDto);

  int updateUser(UserUpdateDto userUpdateDto);

  int addUser(UserAddDto userAddDto);

  int deleteUserById(Long id);

  int updateUserStatus(UserStatusDto userStatusDto);

  UserAdminInfoVo getUserById(Long id);
}

