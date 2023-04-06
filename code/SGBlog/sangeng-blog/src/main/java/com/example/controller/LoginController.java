package com.example.controller;

import com.example.domain.ResponseResult;
import com.example.domain.entity.User;
import com.example.enums.AppHttpCodeEnum;
import com.example.exception.SystemException;
import com.example.service.LoginService;
import com.example.utils.SecurityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
* @ClassName: LoginController
* @Description: 登录（权限验证）
* @author: Zhi
* @date: 2023/3/30 下午2:38
*/
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user) throws Exception {
        if (! StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    @PostMapping("/logout")
    public ResponseResult logout(){
        /*
         * 1. 因为redis中的key由 自定义字符+userId 组成，从redis删掉它需要userId
         * 2. 请求分配到该controller之前，会被jwt过滤器（自己写的）和 SecurityFilterChain拦截。
         *      换句话说，到了该controller的请求都是 携带了token并且token是有效的
         */
        loginService.logout(SecurityUtils.getUserId());
        return ResponseResult.okResult(200, "操作成功");
    }




    @GetMapping("/end")
    public ResponseResult login1() {
        System.out.println(String.join("\n", "|||||||||||||", "响应处理结束", "|||||||||||||"));
        return null;
    }
}
