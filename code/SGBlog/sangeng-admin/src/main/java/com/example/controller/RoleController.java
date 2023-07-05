package com.example.controller;


import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.MethodConstant;
import com.example.domain.ResponseResult;
import com.example.domain.dto.RoleDto;
import com.example.domain.vo.RolePreviewVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.handler.exception.ValidationGroups;
import com.example.service.RoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 角色信息表(Role)表控制层
 *
 * @author Zhi
 * @since 2023-07-04 14:10:02
 */
@Slf4j
@Validated
@RestController
@Api(tags = "登录接口")
@RequestMapping("/system/role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;



    @GetMapping("/list")
    public ResponseResult getRoleList(@Validated(value = ValidationGroups.PageParams.class) PageParams pageParams,
                                      String roleName, String status){
        log.debug("||||| {}::{}, {}, {} |||||", new Exception().getStackTrace()[0].getMethodName(), pageParams.toString(), roleName, status);
        PageResult<RolePreviewVo> rolePreviewVoPageResult = roleService.allRoleList(pageParams, roleName, status);
        return ResponseResult.okResult(rolePreviewVoPageResult);
    }

    @GetMapping("{id}")
    public ResponseResult getRoleById(@PathVariable Long id){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(), id);
        RolePreviewVo rolePreviewVo = roleService.getRoleById(id);
        return ResponseResult.okResult(rolePreviewVo);
    }


    @PutMapping("changeStatus")
    public ResponseResult changeRoleStatus(@Validated(value = ValidationGroups.RoleStatusUpdate.class)
                                           @RequestBody RoleDto roleDto){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(),
            roleDto.toString());
        int ret = roleService.changeRoleStatus(roleDto.getRoleId(), roleDto.getStatus());
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseResult updateRole(@Validated(value = ValidationGroups.RoleUpdate.class)
                                     @RequestBody RoleDto roleDto){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(),
            roleDto.toString());
        int ret = roleService.updateRole(roleDto);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
        }
    }

    @PostMapping("")
    public ResponseResult addRole(@RequestBody RoleDto roleDto){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(), roleDto.toString());
        int ret = roleService.addRole(roleDto);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteRoleById(@PathVariable Long id){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(), id);
        int ret = roleService.deleteRoleById(id);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
        }
    }


}

