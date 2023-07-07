package com.example.mapper;

import com.example.domain.entity.UserRole;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户和角色关联表(UserRole)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-06 10:48:12
 */
@Mapper
//public interface UserRoleMapper extends BaseMapper<UserRole> {
public interface UserRoleMapper extends MppBaseMapper<UserRole> {


}

