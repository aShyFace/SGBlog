package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.constant.RedisConstant;
import com.example.constant.RoleConstant;
import com.example.domain.entity.LoginUser;
import com.example.domain.vo.user.LoginSucceseVo;
import com.example.domain.vo.user.UserInfoVo;
import com.example.mapper.UserMapper;
import com.example.domain.entity.User;
import com.example.service.LoginService;
import com.example.service.RoleService;
import com.example.utils.BeanCopyUtils;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


/**
 * 用户表(User)表服务实现类
 *
 * @author Zhi
 * @since 2023-03-31 13:56:41
 */
@Service("loginService")
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {
//    @Lazy
    @Resource
    private AuthenticationManager authenticationManager;
//    该工具类的方法不是static方法，所以要注入才能使用
    @Resource
    private RedisCache redisCache;
    @Resource
    private RoleService roleService;

    public LoginSucceseVo login(User user, String user_key) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        // 1.这里会调用 UserDetailsService.loadUserByUsername()，然后把方法的返回值给到DaoAuthenticationProvider
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 2.返回认证结果（实现了UserDetails的类对象）
        if ( Objects.isNull(authenticate)){
            throw new RuntimeException("LoginServiceImpl::login 用户名或密码错误");
        }

        // 3.认证成功则利用UserDetails对象生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String id = loginUser.getUser().getId().toString();
        String token = JwtUtil.createJWT(id);

        // 4.然后把用户信息存入redis中，方便下次验证通过时使用
        redisCache.setCacheObject(String.join("", new String[]{user_key, id}), loginUser);
        // 封装响应数据
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        LoginSucceseVo loginSucceseVo = new LoginSucceseVo(token, userInfoVo);
        return loginSucceseVo;
    }


    public void logout(Long userId, String user_key) {
        String[] key = new String[]{user_key, userId.toString()};
        redisCache.deleteObject(String.join("", key));
    }
}
