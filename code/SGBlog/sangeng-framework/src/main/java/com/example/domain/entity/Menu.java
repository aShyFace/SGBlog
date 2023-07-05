package com.example.domain.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 菜单权限表(Menu)表实体类
 *
 * @author Zhi
 * @since 2023-05-31 21:14:51
 */
@TableName(value = "sys_menu")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class Menu {
    @TableId(value = "id") //"菜单ID"
    private Long id;

    @TableField(value = "menu_name") //"菜单名称"
    private String menuName;
    
    @TableField(value = "parent_id") //"父菜单ID"
    private Long parentId;
    
    @TableField(value = "order_num") //"显示顺序"
    private Integer orderNum;
    
    @TableField(value = "path") //"路由地址"
    private String path;
    
    @TableField(value = "component") //"组件路径"
    private String component;
    
    @TableField(value = "is_frame") //"是否为外链（0是 1否）"
    private Integer isFrame;
    
    @TableField(value = "menu_type") //"菜单类型（M目录 C菜单 F按钮）"
    private String menuType;
    
    @TableField(value = "visible") //"菜单状态（0显示 1隐藏）"
    private String visible;
    
    @TableField(value = "status") //"菜单状态（0正常 1停用）"
    private String status;
    
    @TableField(value = "perms") //"权限标识"
    private String perms;
    
    @TableField(value = "icon") //"菜单图标"
    private String icon;
    
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private Long createBy;
    
    @TableField(value = "create_time", fill = FieldFill.INSERT) //"创建时间"
    private Date createTime;
    
    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE) //"更新者"
    private Long updateBy;
    
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE) //"更新时间"
    private Date updateTime;
    
    @TableField(value = "remark") //"备注"
    private String remark;
    
    @TableField(value = "del_flag") //""
    private String delFlag;
    



}

