package com.example.utils;

import com.example.constant.RoleConstant;
import com.example.domain.entity.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @ClassName: SecurityUtils
 * @Description: security工具类
 * @author: Zhi
 * @date: 2023/4/6 上午9:57
 */
public class SecurityUtils
{
    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser()
    {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    // public static void setAuthentication(Authentication authentication){
    //     SecurityContextHolder.getContext().setAuthentication(authentication);
    // }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isRoot(){
        return RoleConstant.ROOT_ROLE_KEY.equals(getLoginUser().getUserRoleKey());
    }

    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }

}
