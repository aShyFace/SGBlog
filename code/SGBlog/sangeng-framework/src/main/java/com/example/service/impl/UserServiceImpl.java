package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.constant.RedisConstant;
import com.example.domain.entity.LoginUser;
import com.example.domain.vo.UserAuthVo;
import com.example.domain.vo.UserVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.exception.SystemException;
import com.example.mapper.UserMapper;
import com.example.domain.entity.User;
import com.example.service.UserService;
import com.example.utils.BeanCopyUilts;
import com.example.utils.RedisCache;
import com.example.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户表(User)表服务实现类
 *
 * @author Zhi
 * @since 2023-04-11 11:20:28
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
//    该工具类的方法不是static方法，所以要注入才能使用
//    @Lazy
    @Resource
    private RedisCache redisCache;
    @Resource
    private AuthenticationManager authenticationManager;


    public User updateUserInfo(UserVo userVo) {
        // 1.修改数据库中的user信息（返回更新后的user对象）
        User user = SecurityUtils.getLoginUser().getUser();
        BeanUtils.copyProperties(userVo, user); // 覆盖user中，userVo里有且值不为空的属性
        int ret = userMapper.updateById(user);
        // 插入失败，则用户不存在
        if (ret == 0){
            throw new SystemException(AppHttpCodeEnum.USER_NOT_EXIST);
        }
        // 2-3.用户存在，则更新redis和SecurityContex中的user数据
        updateUserOfSecurityContextAndRedis(user);
        return user;
    }


    public boolean register(UserAuthVo userAuthVo) {
        if (userNameExist(userAuthVo.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }
        if (nickNameExist(userAuthVo.getNickName())){
            throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        }

        // 加密后存入数据库
        String password = userAuthVo.getPassword();
        userAuthVo.setPassword(passwordEncoder.encode(password));
        User user = BeanCopyUilts.copyBean(userAuthVo, User.class);
        return save(user);
    }


    private void updateUserOfSecurityContextAndRedis(User user){
        // 更新SecurityContex中的user数据
        SecurityUtils.getLoginUser().setUser(user);
        // 更新redis中的user数据
        LoginUser loginUser = SecurityUtils.getLoginUser();
        String key = String.join("", RedisConstant.USER_INFO_KEY, loginUser.getUser().getId().toString());
        redisCache.setCacheObject(key, loginUser);
    }
    private boolean userNameExist(String userName){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserName, userName);
        return count(lqw) > 0;
    }
    private boolean nickNameExist(String nickName){
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getNickName, nickName);
        return count(lqw) > 0;
    }

}
