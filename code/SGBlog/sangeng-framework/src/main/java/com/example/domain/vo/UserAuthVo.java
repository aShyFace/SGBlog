package com.example.domain.vo;

import com.example.handler.exception.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @ClassName: UserAuthVo
 * @Description: 用户登录信息封装类
 * @author: Zhi
 * @date: 2023/4/17 下午5:07
 */
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class UserAuthVo {
    @NotBlank(message = "登录时，用户名不能为空", groups = {ValidationGroups.UserLoginPassword.class})
    @NotBlank(message = "注册时，用户名不能为空", groups = {ValidationGroups.UserInsert.class})
    private String userName;

    @NotBlank(message = "注册时，昵称不能为空", groups = {ValidationGroups.UserInsert.class})
    private String nickName;

    @NotBlank(message = "登录时，密码不能为空", groups = {ValidationGroups.UserLoginPassword.class})
    @NotBlank(message = "注册时，密码不能为空", groups = {ValidationGroups.UserInsert.class})
    @Pattern(message = "密码:6-16位大 小写字母、数字、特殊字符", regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!#$%^&*.])[\\da-zA-Z!#$%^&*.]{6,16}$", groups = {ValidationGroups.UserInsert.class})
    private String password;

    @NotBlank(message = "注册时，邮箱不能为空", groups = {ValidationGroups.UserInsert.class})
    @Email
    private String email;

    @NotBlank(message = "登录时，验证码不能为空", groups = {ValidationGroups.UserLoginCode.class})
    // @NotBlank(message = "注册时，验证码不能为空", groups = {ValidationGroups.UserInsert.class})
    public String  identifyingCode;
}
