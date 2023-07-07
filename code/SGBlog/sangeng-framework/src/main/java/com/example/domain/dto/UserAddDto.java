package com.example.domain.dto;


import com.example.handler.exception.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@ApiModel(value = "用户登录实体类")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class UserAddDto {
  @ApiModelProperty(value = "用户名")
  @NotBlank(message = "添加用户时，用户名不能为空", groups = {ValidationGroups.UserAdminInsert.class})
  private String userName;

  @ApiModelProperty(value = "昵称")
  @NotBlank(message = "添加用户时，昵称不能为空", groups = {ValidationGroups.UserAdminInsert.class})
  private String nickName;

  @ApiModelProperty(value = "密码")
  @NotBlank(message = "添加用户时，密码不能为空", groups = {ValidationGroups.UserAdminInsert.class})
  @Pattern(message = "密码:6-16位大 小写字母、数字、特殊字符", regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!#$%^&*.])[\\da-zA-Z!#$%^&*.]{6,16}$", groups = {ValidationGroups.UserAdminInsert.class})
  private String password;

  @NotBlank(message = "注册时，邮箱不能为空", groups = {ValidationGroups.UserAdminInsert.class})
  @ApiModelProperty(value = "邮箱")
  @Email
  private String email;

  @ApiModelProperty(value = "手机号")
  @NotBlank(message = "手机号不能为空")
  @Pattern(regexp = "^[1][3-9]\\d{9}$", message = "请输入正确手机号手机号")
  private String phonenumber;

  @ApiModelProperty(value = "用户性别（0男，1女，2未知）")
  private String sex;

  @ApiModelProperty(value = "账号状态（0正常 1停用）")
  private String status;

  @ApiModelProperty(value = "角色列表")
  private List<Long> roleIds;
}
