package com.example.controller;


import com.example.domain.ResponseResult;
import com.example.domain.dto.UserAuthDto;
import com.example.domain.dto.UserInfoDto;
import com.example.domain.entity.User;
import com.example.domain.vo.UserInfoVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.handler.exception.ValidationGroups;
import com.example.service.UserService;
import com.example.utils.BeanCopyUilts;
import com.example.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 用户表(User)表控制层
 *
 * @author Zhi
 * @since 2023-04-11 11:22:58
 */
@Slf4j
@Validated
@RestController
@Api(tags = "用户相关接口")
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @GetMapping("/userInfo")
    @ApiOperation(value = "查看用户信息")
    public ResponseResult userInfo(){
        User user = SecurityUtils.getLoginUser().getUser();
        if (Objects.nonNull(user)){
            return ResponseResult.okResult(BeanCopyUilts.copyBean(user, UserInfoVo.class));
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
    }

    /*
        put请求的 请求体使用的格式是表单，也就是from-data，它用key，value的方式存储数据，
        所以controller的方法参数要添加@RequestBody。请求体格式为json的话，就不用加了
    */
    @PutMapping("/userInfo")
    @ApiOperation(value = "更新用户信息")
    public ResponseResult updateUserInfo(@NotNull @RequestBody UserInfoDto userInfoDto){
        // 此时的用户信息有三份。security，数据库，redis。更新用户信息的时候，理论上这三个地方的数据都要修改
        userService.updateUserInfo(userInfoDto);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public ResponseResult register(@Validated(value = ValidationGroups.UserInsert.class) @RequestBody UserAuthDto userAuthDto){
        if (userService.register(userAuthDto)){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
    }
}

