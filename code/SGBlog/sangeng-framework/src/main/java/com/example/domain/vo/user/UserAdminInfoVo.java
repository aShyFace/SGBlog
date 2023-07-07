package com.example.domain.vo.user;

import com.example.domain.vo.RoleVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public class UserAdminInfoVo {
  @ApiModelProperty(value = "角色id列表")
  private List<Long> roleIds;

  @ApiModelProperty(value = "角色列表")
  private List<RoleVo> roles;

  @ApiModelProperty(value = "角色信息")
  private UserAdminChildVo user;
}


