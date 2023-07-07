package com.example.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import java.util.List;

@ApiModel(value = "用户登录实体类")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class UserUpdateDto {
  @ApiModelProperty(value = "id")
  private Long id;

  @ApiModelProperty(value = "用户名")
  private String userName;

  @ApiModelProperty(value = "昵称")
  private String nickName;

  @Email
  @ApiModelProperty(value = "邮箱")
  private String email;

  @ApiModelProperty(value = "用户性别（0男，1女，2未知）")
  private String sex;

  @ApiModelProperty(value = "账号状态（0正常 1停用）")
  private String status;

  @ApiModelProperty(value = "角色列表")
  private List<Long> roleIds;
}
