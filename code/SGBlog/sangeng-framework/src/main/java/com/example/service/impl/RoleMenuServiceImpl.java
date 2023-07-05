package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.RoleMenuMapper;
import com.example.domain.entity.RoleMenu;
import com.example.service.RoleMenuService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 角色和菜单关联表(RoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2023-07-05 10:39:35
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Resource
    private RoleMenuMapper roleMenuMapper;
}   

