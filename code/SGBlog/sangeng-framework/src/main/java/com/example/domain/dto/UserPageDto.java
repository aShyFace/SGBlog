package com.example.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;


@ApiModel(value = "用户登录实体类")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class UserPageDto {
  @ApiModelProperty(value = "用户名")
  private String userName;

  @ApiModelProperty(value = "手机号")
  //@Pattern(regexp = "^[1][3-9]\\d{9}$", message = "请输入正确手机号手机号")
  private String phonenumber;

  @ApiModelProperty(value = "账号状态（0正常 1停用）")
  private String status;

}
