package com.example.controller;




import com.example.domain.ResponseResult;
import com.example.domain.entity.User;
import com.example.domain.vo.UserAuthVo;
import com.example.domain.vo.UserVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.handler.exception.ValidationGroups;
import com.example.service.LoginService;
import com.example.service.UserService;
import com.example.utils.BeanCopyUilts;
import com.example.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
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
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;
    @Resource
    private LoginService loginService;

    @GetMapping("/userInfo")
    @ApiOperation(value = "查看用户信息")
    public ResponseResult userInfo(){
        User user = SecurityUtils.getLoginUser().getUser();
        if (Objects.nonNull(user)){
            return ResponseResult.okResult(BeanCopyUilts.copyBean(user, UserVo.class));
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
    }

    /*
        put请求的 请求体使用的格式是表单，也就是from-data，它用key，value的方式存储数据，
        所以controller的方法参数要添加@RequestBody。请求体格式为json的话，就不用加了
    */
    @PutMapping("/userInfo")
    @ApiOperation(value = "更新用户信息")
    public ResponseResult updateUserInfo(@NotNull @RequestBody UserVo userVo){
        // 此时的用户信息有三份。security，数据库，redis。更新用户信息的时候，理论上这三个地方的数据都要修改
        userService.updateUserInfo(userVo);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @PostMapping("/register")
    @ApiOperation(value = "用户注册")
    public ResponseResult register(@Validated(value = ValidationGroups.UserInsert.class) @RequestBody UserAuthVo userAuthVo){
        if (userService.register(userAuthVo)){
            return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
    }
}

