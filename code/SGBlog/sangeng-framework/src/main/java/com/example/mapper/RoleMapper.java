package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色信息表(Role)表数据库访问层
 *
 * @author Zhi
 * @since 2023-05-31 21:57:55
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectRoleKeyByUserId(@Param("userId")Long userId, @Param("status") String status, @Param("del_flag") String del_flag);
}

