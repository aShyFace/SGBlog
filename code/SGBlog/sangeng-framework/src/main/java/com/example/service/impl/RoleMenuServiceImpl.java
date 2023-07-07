package com.example.service.impl;

import com.example.domain.entity.RoleMenu;
import com.example.mapper.RoleMenuMapper;
import com.example.service.RoleMenuService;
import com.github.jeffreyning.mybatisplus.service.MppServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2023-07-05 10:39:35
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends MppServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Resource
    private RoleMenuMapper roleMenuMapper;
}   

