package com.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

/**
 * @ClassName: LoginUser
 * @Description:
 * @author: Zhi
 * @date: 2023/3/31 下午2:53
 */
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class LoginUser implements UserDetails {
    private User user;
//    public LoginUser(User user) {
//        this.user = user;
//    }

//    返回权限集合
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        if (Objects.isNull(user)){
            user = new User();
            user.setPassword("1234");
        }
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        if (Objects.isNull(user)){
            user = new User();
            user.setUserName("user");
        }
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
