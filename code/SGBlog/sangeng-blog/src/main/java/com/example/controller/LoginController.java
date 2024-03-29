package com.example.controller;

import com.example.constant.RedisConstant;
import com.example.domain.ResponseResult;
import com.example.domain.dto.UserAuthDto;
import com.example.domain.entity.User;
import com.example.domain.vo.user.LoginSucceseVo;
import com.example.handler.exception.ValidationGroups;
import com.example.service.LoginService;
import com.example.utils.BeanCopyUtils;
import com.example.utils.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
* @ClassName: LoginController
* @Description: 登录（权限验证）
* @author: Zhi
* @date: 2023/3/30 下午2:38
*/
@Slf4j
@Validated
@RestController
@Api(tags = "登录接口")
@CrossOrigin(origins = "*")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@Validated(value = ValidationGroups.UserLoginPassword.class) @RequestBody UserAuthDto userAuthDto) throws Exception {
        LoginSucceseVo loginSucceseVo = loginService.login(BeanCopyUtils.copyBean(userAuthDto, User.class), RedisConstant.USER_INFO_KEY);
        return ResponseResult.okResult(loginSucceseVo);
    }

     @PostMapping("/logout")
    public ResponseResult logout(){
        /*
         * 1. 因为redis中的key由 自定义字符+userId 组成，从redis删掉它需要userId
         * 2. 请求分配到该controller之前，会被jwt过滤器（自己写的）和 SecurityFilterChain拦截。
         *      换句话说，到了该controller的请求都是 携带了token并且token是有效的
         */
        loginService.logout(SecurityUtils.getUserId(), RedisConstant.USER_INFO_KEY);
        return ResponseResult.okResult(200, "操作成功");
    }


}
