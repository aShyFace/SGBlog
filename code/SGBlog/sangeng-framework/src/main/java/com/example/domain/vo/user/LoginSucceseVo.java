package com.example.domain.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: LoginSucceseVo
 * @Description: 登录成功后返回用户信息
 * @author: Zhi
 * @date: 2023/5/29 下午2:21
 */
@ApiModel(value = "登录成功后返回用户信息")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class LoginSucceseVo {
    @ApiModelProperty(value = "服务器返回的token")
    private String token;

    @ApiModelProperty(value = "存储在redis中的用户信息")
    private UserInfoVo userInfo;
}
