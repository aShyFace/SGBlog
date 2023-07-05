package com.example.domain.dto;


import java.util.Date;

import com.example.handler.exception.ValidationGroups;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户表(User)表实体类
 *
 * @author Zhi
 * @since 2023-03-30 11:13:05
 */
@ApiModel(value = "用户信息实体类")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class UserInfoDto {
    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户性别（0男，1女，2未知）")
    private String sex;

    @ApiModelProperty(value = "头像")
    private String avatar;

}

