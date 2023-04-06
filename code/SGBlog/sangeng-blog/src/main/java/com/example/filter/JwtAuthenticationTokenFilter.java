package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.example.constant.RedisConstant;
import com.example.domain.ResponseResult;
import com.example.domain.entity.LoginUser;
import com.example.enums.AppHttpCodeEnum;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import com.example.utils.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @ClassName: JwtAuthenticationTokenFilter
 * @Description: 用户访问后台接口之前，需要对其进行判断
 *      - 携带了token就去判断token的合法性，合法就把信息存到SecurityContextHolder中；不合法重新登录
 *      - 未携带token的，走新用户未登录的流程（对应代码的逻辑就是 直接放行给UserDetailsService处理）
 * @author: Zhi
 * @date: 2023/4/2 下午3:09
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1.获取请求头中的token
        String token = request.getHeader("token");
        if(!StringUtils.hasText(token)){
            // 处理预检请求
            if ("/logout".equals(request.getServletPath())) {
                ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.setHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET");
                response.setHeader("Access-Control-Max-Age", "3600");
                response.setHeader("Access-Control-Allow-Headers", "*");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                // response.setHeader("Access-Control-Allow-Origin", "http://192.168.10.118:8070");
                WebUtils.renderString(response, JSON.toJSONString(result));
            }
            //说明该接口不需要登录 直接放行
            /*
            *   看这里：
            *       1. OncePerRequestFilter 只是SecurityFilterChain的其中一个过滤器，在OncePerRequestFilter后面还有很多Filter，它放行 != 其它过滤器也放行
            *       2. authorizeRequests配置的过滤器就在OncePerRequestFilter后面，不匹配authorizeRequests的该拦截还是被拦截
            *       3. 不匹配authorizeRequests的请求，会得到4开头的响应，内容大概是要你登录
            * */
            filterChain.doFilter(request, response);
            return;
        }

        // 2.解析请求携带过来的token，从而获取userid
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            //token超时  token非法
            //响应告诉前端需要重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }

        // 3.从redis中获取用户信息
        String[] key = new String[]{RedisConstant.USER_INFO_KEY, claims.getSubject()};
        LoginUser loginUser = redisCache.getCacheObject(String.join("", key));
        // 如果获取不到
        if(Objects.isNull(loginUser)){
            // 说明登录过期  提示重新登录
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            // 设置响应头,同时把返回值(字符串)写入响应体中
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }

        // 4.用户存在,则把查询出的UserDetail对象存入SecurityContextHolder
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null); //三个参数的方法代表该用户已认证
        SecurityContext securityContext = SecurityContextHolder.getContext();
        // if (Objects.isNull(securityContext)){
        //     SecurityContextHolder.setContext(new SecurityContextImpl());
        // }
        securityContext.setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }


}