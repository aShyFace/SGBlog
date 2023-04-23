package com.example.domain.vo;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 用户表(User)表实体类
 *
 * @author Zhi
 * @since 2023-03-30 11:13:05
 */
@ApiModel(value="用户信息实体类")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class UserInfoVo {
    @ApiModelProperty(value="用户名")
    private String userName;

    @ApiModelProperty(value="昵称")
    private String nickName;

    @ApiModelProperty(value="邮箱")
    private String email;

    @ApiModelProperty(value="用户性别（0男，1女，2未知）")
    private String sex;

    @ApiModelProperty(value="头像")
    private String avatar;

}

