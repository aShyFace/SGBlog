package com.example.domain.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@ApiModel(value = "分类Vo")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class CategoryUpdateDto {
  @TableId(value = "id")
  // @ApiModelProperty(value = "")
  private Long id;

  @TableField(value = "name")
  // @ApiModelProperty(value = "分类名")
  private String name;

  @TableField(value = "pid")
  // @ApiModelProperty(value = "父分类id，如果没有父分类为-1")
  private Long pid;

  @TableField(value = "description")
  // @ApiModelProperty(value = "描述")
  private String description;

  @TableField(value = "status")
  // @ApiModelProperty(value = "状态0:正常,1禁用")
  private String status;
}
