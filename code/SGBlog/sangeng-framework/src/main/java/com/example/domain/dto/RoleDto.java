package com.example.domain.dto;


import com.example.handler.exception.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 角色信息表(Role)表实体类
 *
 * @author Zhi
 * @since 2023-05-31 21:57:55
 */
@ApiModel(value = "Role对象", description = "角色信息表")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class RoleDto {
    @ApiModelProperty(value = "角色ID")
    //@NotNull(message = "修改status时，id不能为空", groups = ValidationGroups.RoleStatusUpdate.class)
    private Long roleId;

    @ApiModelProperty(value = "角色ID")
    //@NotNull(message = "修改status时，id不能为空", groups = ValidationGroups.RoleStatusUpdate.class)
    private Long Id;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色权限字符串")
    private String roleKey;

    @ApiModelProperty(value = "显示顺序")
    private Integer roleSort;

    @ApiModelProperty(value = "角色状态（0正常 1停用）")
    @NotBlank(message = "status不能为空", groups = ValidationGroups.RoleStatusUpdate.class)
    private String status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "菜单id")
    @NotNull(message = "新建角色时，menuId不能为空", groups = ValidationGroups.RoleInsert.class)
    private List<Long> menuIds;
}

