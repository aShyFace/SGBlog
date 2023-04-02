package com.example.controller;

import com.example.domain.ResponseResult;
import com.example.domain.entity.User;
import com.example.enums.AppHttpCodeEnum;
import com.example.service.LoginService;
import org.omg.CORBA.SystemException;
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

    @PostMapping("/hello")
    public ResponseResult login(@RequestBody User user) throws Exception {
        if (! StringUtils.hasText(user.getUserName())){
            throw new Exception(AppHttpCodeEnum.REQUIRE_USERNAME.toString());
        }
        return loginService.login(user);
    }

    @GetMapping("/end")
    public ResponseResult login1() {
        System.out.println(String.join("\n", "|||||||||||||", "响应处理结束", "|||||||||||||"));
        return null;
    }
}
