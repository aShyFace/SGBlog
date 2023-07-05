package com.example.domain.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Repository;

/**
 * 菜单权限表(Menu)表实体类
 *
 * @author Zhi
 * @since 2023-05-31 21:14:51
 */
@ApiModel(value = "Menu对象", description = "菜单预览")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
@Repository
public class MenuPreviewVo {
  @ApiModelProperty(value = "菜单ID")
  private Long id;

  @ApiModelProperty(value = "菜单名称")
  private String menuName;

  @ApiModelProperty(value = "父菜单ID")
  private Long parentId;

  @ApiModelProperty(value = "显示顺序")
  private Integer orderNum;

  @ApiModelProperty(value = "路由地址")
  private String path;

  @ApiModelProperty(value = "菜单类型（M目录 C菜单 F按钮）")
  private String menuType;

  @ApiModelProperty(value = "菜单状态（0显示 1隐藏）")
  private String visible;

  @ApiModelProperty(value = "菜单状态（0正常 1停用）")
  private String status;

  @ApiModelProperty(value = "权限标识")
  private String perms;

  @ApiModelProperty(value = "菜单图标")
  private String icon;

}
