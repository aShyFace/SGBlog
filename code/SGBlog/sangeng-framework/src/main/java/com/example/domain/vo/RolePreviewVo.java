package com.example.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;


@ApiModel(value = "Role对象", description = "角色信息表")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class RolePreviewVo {
  @ApiModelProperty(value = "角色ID")
  private Long id;

  @ApiModelProperty(value = "角色名称")
  private String roleName;

  @ApiModelProperty(value = "角色权限字符串")
  private String roleKey;

  @ApiModelProperty(value = "显示顺序")
  private Integer roleSort;

  @ApiModelProperty(value = "角色状态（0正常 1停用）")
  private String status;

  @TableField(value = "create_time", fill = FieldFill.INSERT) //"创建时间"
  private Date createTime;
}
