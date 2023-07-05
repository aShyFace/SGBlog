package com.example.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Repository;

import java.util.List;

@ApiModel(value = "Menu对象", description = "菜单预览")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@Repository
public class MenuTreeselectVo {
  @ApiModelProperty(value = "菜单ID")
  private Long id;

  @ApiModelProperty(value = "菜单名称")
  private String menuName;

  @ApiModelProperty(value = "菜单名称, menuName")
  private String label;

  @ApiModelProperty(value = "父菜单ID")
  private Long parentId;

  @ApiModelProperty(value = "父菜单ID")
  private List<MenuTreeselectVo> children;
}
