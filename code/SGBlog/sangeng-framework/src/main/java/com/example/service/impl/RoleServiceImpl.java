package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.DataBaseConstant;
import com.example.constant.MenuConstant;
import com.example.constant.MethodConstant;
import com.example.constant.RoleConstant;
import com.example.domain.dto.RoleDto;
import com.example.domain.entity.Role;
import com.example.domain.entity.RoleMenu;
import com.example.domain.vo.RoleManegerVo;
import com.example.domain.vo.RolePreviewVo;
import com.example.mapper.RoleMapper;
import com.example.service.RoleMenuService;
import com.example.service.RoleService;
import com.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
    @Resource
    private RoleMenuService roleMenuService;

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

    @Override
    public int changeRoleStatus(Long roleId, String status) {
        Role role = new Role();
        role.setStatus(status).setId(roleId);
        int ret = roleMapper.updateById(role);
        return ret;
    }

    @Override
    public RolePreviewVo getRoleById(Long id) {
        Role role = roleMapper.selectById(id);
        RolePreviewVo rolePreviewVo = BeanCopyUtils.copyBean(role, RolePreviewVo.class);
        return rolePreviewVo;
    }

    @Override
    public PageResult<RolePreviewVo> getRolePage(PageParams pageParams, String roleName, String status) {
        LambdaQueryWrapper<Role> lqw = new LambdaQueryWrapper<Role>().orderByAsc(Role::getRoleSort);
        if (Objects.nonNull(roleName)){
            lqw.like(Role::getRoleName, roleName);
        }
        if (StringUtils.hasText(status)) {
            lqw.eq(Role::getStatus , status);
        }
        Page page = new Page(pageParams.getPageNum(), pageParams.getPageSize());
        List<Role> roleList = page(page, lqw).getRecords();
        List<RolePreviewVo> rolePreviewVoList = BeanCopyUtils.copyBeanList(roleList, RolePreviewVo.class);
        return new PageResult<>(rolePreviewVoList, page.getTotal(), pageParams);
    }

    @Transactional
    public int updateRole(RoleDto roleDto) {
        Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
        int ret = roleMapper.updateById(role);

        List<RoleMenu> roleMenuList = roleDto.getMenuIds().stream().map(
          menuId -> new RoleMenu(role.getId(), menuId)
        ).collect(Collectors.toList());
        roleMenuService.saveOrUpdateBatchByMultiId(roleMenuList);
        return MethodConstant.ERROR != ret ? MethodConstant.SUCCESS:MethodConstant.SUCCESS;
    }

    @Override
    public List<RoleManegerVo> allRoleList() {
        LambdaQueryWrapper<Role> lqw = new LambdaQueryWrapper<>();
        List<Role> roleList = roleMapper.selectList(lqw.eq(Role::getStatus, RoleConstant.ROLE_STATUS_IS_NORMAL));
        List<RoleManegerVo> roleManegerVoList = BeanCopyUtils.copyBeanList(roleList, RoleManegerVo.class);
        return roleManegerVoList;
    }

    @Transactional
    public int addRole(RoleDto roleDto) {
        Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
        int ret = roleMapper.insert(role);

        Long roleId = role.getId();
        List<RoleMenu> roleMenuList = roleDto.getMenuIds().stream().map(
          menuId -> new RoleMenu(roleId, menuId)
        ).collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenuList);
        return MethodConstant.ERROR != ret ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }

    @Transactional
    public int deleteRoleById(Long id) {
        LambdaQueryWrapper<Role> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Role::getId, id);
        if (count(lqw) > 0){ // 大于0表示未删除
            roleMapper.deleteById(id); //逻辑删除
            LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
            roleMenuService.getBaseMapper().delete(queryWrapper.eq(RoleMenu::getRoleId, id));
        }
        return MethodConstant.SUCCESS;
    }



    // public boolean userIsRoot(Long userId) {
    //     return false;
    // }


}
