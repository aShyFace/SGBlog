package com.example.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 角色和菜单关联表(RoleMenu)表实体类
 *
 * @author Zhi
 * @since 2023-07-05 10:39:12
 */
@TableName(value="sys_role_menu")
@SuppressWarnings(value={"serial", "unused"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class RoleMenu {
    //@TableId(value = "roleId") //角色ID
    private Long roleId;

    //@TableId(value = "menuId") //菜单ID
    private Long menuId;





}

