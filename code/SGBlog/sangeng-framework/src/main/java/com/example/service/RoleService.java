package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.entity.Role;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author Zhi
 * @since 2023-05-31 21:57:55
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long userId);

}

