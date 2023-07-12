package com.example.controller;


import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.MethodConstant;
import com.example.domain.ResponseResult;
import com.example.domain.dto.UserAddDto;
import com.example.domain.dto.UserPageDto;
import com.example.domain.dto.UserStatusDto;
import com.example.domain.dto.UserUpdateDto;
import com.example.domain.vo.user.UserAdminInfoVo;
import com.example.domain.vo.user.UserPreviewVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.handler.exception.ValidationGroups;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

/**
 * 用户表(User)表控制层
 *
 * @author Zhi
 * @since 2023-07-05 21:14:59
 */
@Slf4j
@Validated
@RestController
@Api(tags = "登录接口")
@RequestMapping("/system/user")
//@CrossOrigin(origins = "*")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @GetMapping("/list")
    public ResponseResult getUserList(@Validated(value = ValidationGroups.PageParams.class) PageParams pageParams,
                                      UserPageDto userPageDto){
        log.debug("||||| {}::{}, {} |||||", new Exception().getStackTrace()[0].getMethodName(),
            pageParams.toString(), userPageDto.toString());
        PageResult<UserPreviewVo> userPreviewVoPage = userService.getUserList(pageParams, userPageDto);
        return ResponseResult.okResult(userPreviewVoPage);
    }

    @GetMapping("/{id}")
    public ResponseResult getUserById(@PathVariable Long id){
        log.debug("||||| {}::{}, {} |||||", new Exception().getStackTrace()[0].getMethodName(), id);
        UserAdminInfoVo userAdminInfoVo = userService.getUserById(id);
        return ResponseResult.okResult(userAdminInfoVo);
        //return null;
    }

    @PostMapping("")
    @ApiOperation(value = "添加用户和删除用户的时候，都需要把角色表和关联表中的数据删除")
    public ResponseResult addUser(@RequestBody UserAddDto userAddDto){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(), userAddDto.toString());
        int ret = userService.addUser(userAddDto);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseResult updateUser(@Validated(value = ValidationGroups.UserAdminInsert.class)
                                     @RequestBody UserUpdateDto userUpdateDto){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(),
            userUpdateDto.toString());
        int ret = userService.updateUser(userUpdateDto);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.UPDATE_ERROR);
        }
    }

    @PutMapping("/changeStatus")
    public ResponseResult updateUser(@RequestBody UserStatusDto userStatusDto){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(),
            userStatusDto.toString());
        int ret = userService.updateUserStatus(userStatusDto);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.UPDATE_ERROR);
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "添加用户和删除用户的时候，都需要把角色表和关联表中的数据删除")
    public ResponseResult deleteUserById(@Min(1L) @PathVariable Long id){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(), id);
        int ret = userService.deleteUserById(id);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DELETE_ERROR);
        }
    }

}

