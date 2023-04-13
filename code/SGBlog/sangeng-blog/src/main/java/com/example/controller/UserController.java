package com.example.controller;




import com.example.domain.ResponseResult;
import com.example.domain.entity.User;
import com.example.domain.vo.UserVo;
import com.example.service.UserService;
import com.example.utils.BeanCopyUilts;
import com.example.utils.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * 用户表(User)表控制层
 *
 * @author Zhi
 * @since 2023-04-11 11:22:58
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @GetMapping("/userInfo")
    public ResponseResult userInfo(){
        User user = SecurityUtils.getLoginUser().getUser();
        if (Objects.nonNull(user)){
            return ResponseResult.okResult(BeanCopyUilts.copyBean(user, UserVo.class));
        }
        return ResponseResult.errorResult(500, "UserController::userInfo 出错");
    }
}

