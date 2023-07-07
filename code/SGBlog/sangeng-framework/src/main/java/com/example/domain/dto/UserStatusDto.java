package com.example.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@ApiModel(value = "用户登录实体类")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class UserStatusDto {
  @ApiModelProperty(value = "id")
  @NotNull(message = "id不能为空")
  private Long userId;

  @ApiModelProperty(value = "账号状态（0正常 1停用）")
  @NotNull(message = "status不能为空")
  private String status;
}
