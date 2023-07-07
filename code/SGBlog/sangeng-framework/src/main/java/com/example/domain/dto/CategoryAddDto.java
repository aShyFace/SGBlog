package com.example.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

@ApiModel(value = "分类Vo")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class CategoryAddDto {
  @ApiModelProperty(value = "分类名")
  private String name;

  @ApiModelProperty(value = "描述")
  private String description;

  @ApiModelProperty(value = "状态0:正常,1禁用")
  private String status;
}
