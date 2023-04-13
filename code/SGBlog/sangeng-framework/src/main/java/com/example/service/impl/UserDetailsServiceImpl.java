package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.domain.entity.LoginUser;
import com.example.domain.entity.User;
import com.example.mapper.UserMapper;
import com.example.utils.BeanCopyUilts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @ClassName: UserDetailsServiceImpl
 * @Description: UserDetailsService实现类
 * @author: Zhi
 * @date: 2023/3/31 下午2:41
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
//public class UserDetailsServiceImpl {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        lqw.eq(User::getUserName, username);
        User user = userMapper.selectOne(lqw);
        if (Objects.isNull(user)){
            throw new RuntimeException("账号或密码错误");
        }

//        查询结果封装成UserDetails的类对象
        LoginUser loginUser = new LoginUser(user);
        return loginUser;
    }
}
