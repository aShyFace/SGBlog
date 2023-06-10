package com.example.controller;

import com.example.constant.RedisConstant;
import com.example.constant.RoleConstant;
import com.example.domain.ResponseResult;
import com.example.domain.dto.UserAuthDto;
import com.example.domain.entity.LoginUser;
import com.example.domain.entity.User;
import com.example.domain.vo.MenuVo;
import com.example.domain.vo.user.AdminUserInfoVo;
import com.example.domain.vo.user.LoginSucceseVo;
import com.example.domain.vo.user.UserInfoVo;
import com.example.handler.exception.ValidationGroups;
import com.example.service.LoginService;
import com.example.service.MenuService;
import com.example.service.RoleService;
import com.example.utils.BeanCopyUtils;
import com.example.utils.SecurityUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
* @ClassName: AdminLoginController
* @Description: 登录（权限验证）
* @author: Zhi
* @date: 2023/3/30 下午2:38
*/
@Slf4j
@Validated
@RestController
@Api(tags = "登录接口")
public class AdminLoginController {
    @Resource
    private LoginService loginService;
    @Resource
    private MenuService menuService;
    @Resource
    private RoleService roleService;
    @Resource
    private AuthenticationManager authenticationManager;


    @PostMapping("/user/login")
    public ResponseResult login(@Validated(value = ValidationGroups.UserLoginPassword.class) @RequestBody UserAuthDto userAuthDto) throws Exception {
        LoginSucceseVo loginSucceseVo = loginService.login(BeanCopyUtils.copyBean(userAuthDto, User.class), RedisConstant.ADMIN_INFO_KEY);
        return ResponseResult.okResult(loginSucceseVo);
    }


    @GetMapping("/getInfo")
    public ResponseResult getInfo(){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUser().getId();
        boolean userIsRoot = loginUser.getUserRoleKey().equals(RoleConstant.ROOT_ROLE_KEY);
        //根据用户id查询角色信息
        List<String> roleKeyList = userIsRoot? new ArrayList<>(Arrays.asList(RoleConstant.ROOT_ROLE_KEY)) : roleService.selectRoleKeyByUserId(userId);
        //根据用户id查询权限信息
        List<String> perms = menuService.selectMenusByUserId(userId, userIsRoot);

        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //封装数据返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
        // return null;
    }


    @GetMapping("/getRouters")
    public ResponseResult getRouters(){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUser().getId();
        boolean userIsRoot = loginUser.getUserRoleKey().equals(RoleConstant.ROOT_ROLE_KEY);
        //根据用户id查询权限信息
        List<MenuVo> routerMenuTree = menuService.selectRouterMenuTreeByUserId(userId, userIsRoot);
        // List<MenuVo> routerMenuTree = menuService.selectRouterMenuTreeByUserId(1L, true);
        HashMap<String, List<MenuVo>> menus = new HashMap<>();
        menus.put("menus", routerMenuTree);
        return ResponseResult.okResult(menus);
    }


    @PostMapping("/user/logout")
    public ResponseResult logout(){
        /*
         * 1. 因为redis中的key由 自定义字符+userId 组成，从redis删掉它需要userId
         * 2. 请求分配到该controller之前，会被jwt过滤器（自己写的）和 SecurityFilterChain拦截。
         *      换句话说，到了该controller的请求都是 携带了token并且token是有效的
         */
        loginService.logout(SecurityUtils.getUserId());
        return ResponseResult.okResult(200, "操作成功");
    }


}
