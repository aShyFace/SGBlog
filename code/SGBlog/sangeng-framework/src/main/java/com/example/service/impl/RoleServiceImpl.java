package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.constant.DataBaseConstant;
import com.example.constant.MenuConstant;
import com.example.constant.RoleConstant;
import com.example.mapper.RoleMapper;
import com.example.domain.entity.Role;
import com.example.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author Zhi
 * @since 2023-05-31 21:57:55
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    public List<String> selectRoleKeyByUserId(Long userId) {
        List<Role> roleList = roleMapper.selectRoleKeyByUserId(userId, MenuConstant.MENU_STATUS_IS_NORMAL, DataBaseConstant.ROW_IS_NOT_DELETE);
        List<String> roleKeyList = roleList.stream().map(
            Role::getRoleKey
        ).collect(Collectors.toList());

        if (roleKeyList.contains(RoleConstant.ROOT_ROLE_KEY)){
            roleKeyList = new ArrayList<>(Arrays.asList(RoleConstant.ROOT_ROLE_KEY));
        }
        return roleKeyList;
    }


    // public boolean userIsRoot(Long userId) {
    //     return false;
    // }


}
