package com.example.service.impl;

import com.example.domain.entity.UserRole;
import com.example.mapper.UserRoleMapper;
import com.example.service.UserRoleService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2023-07-06 10:48:12
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends MppServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
//public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
  @Resource
  private UserRoleMapper userRoleMapper;
}   

