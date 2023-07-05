package com.example.domain.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 角色信息表(Role)表实体类
 *
 * @author Zhi
 * @since 2023-05-31 21:57:55
 */
@TableName(value = "sys_role")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class Role {
    @TableId(value = "id") //"角色ID"
    private Long id;

    @TableField(value = "role_name") //"角色名称"
    private String roleName;
    
    @TableField(value = "role_key") //"角色权限字符串"
    private String roleKey;
    
    @TableField(value = "role_sort") //"显示顺序"
    private Integer roleSort;
    
    @TableField(value = "status") //"角色状态（0正常 1停用）"
    private String status;
    
    @TableField(value = "del_flag") //"删除标志（0代表存在 1代表删除）"
    private String delFlag;
    
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
    



}

