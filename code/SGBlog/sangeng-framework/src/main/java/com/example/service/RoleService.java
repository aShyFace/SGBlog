package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.domain.dto.RoleDto;
import com.example.domain.entity.Role;
import com.example.domain.vo.RoleManegerVo;
import com.example.domain.vo.RolePreviewVo;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author Zhi
 * @since 2023-05-31 21:57:55
 */
public interface RoleService extends IService<Role> {

  List<String> selectRoleKeyByUserId(Long userId);



  RolePreviewVo getRoleById(Long id);

  int changeRoleStatus(Long roleId, String status);

  int addRole(RoleDto roleDto);

  int deleteRoleById(Long id);

  PageResult<RolePreviewVo> getRolePage(PageParams pageParams, String roleName, String status);

  int updateRole(RoleDto roleDto);

  List<RoleManegerVo> allRoleList();
}

