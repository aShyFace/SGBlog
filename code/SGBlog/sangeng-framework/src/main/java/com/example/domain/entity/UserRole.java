package com.example.domain.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 用户和角色关联表(UserRole)表实体类
 *
 * @author Zhi
 * @since 2023-07-06 10:47:50
 */
@TableName(value="sys_user_role")
@SuppressWarnings(value={"serial", "unused"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class UserRole {
    @MppMultiId
    @TableField(value = "user_id")
    //@TableId(value = "userId") //用户ID
    private Long userId;

    @MppMultiId
    @TableField(value = "role_id")
    //@TableId(value = "roleId") //角色ID
    private Long roleId;


}

