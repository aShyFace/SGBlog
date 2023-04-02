package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.entity.User;

/**
 * 用户表(User)表数据库访问层
 *
 * @author Zhi
 * @since 2023-03-30 11:13:05
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

