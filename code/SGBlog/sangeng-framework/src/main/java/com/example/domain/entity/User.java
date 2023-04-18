package com.example.domain.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.example.handler.exception.ValidationGroups;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户表(User)表实体类
 *
 * @author Zhi
 * @since 2023-03-30 11:13:05
 */
@TableName(value="sys_user")
@ApiModel(value="User对象", description="用户表")
@SuppressWarnings(value={"serial", "unused"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class User {
    @TableId(value="id")
    @ApiModelProperty(value="主键")
    private Long id;

    @TableField(value="user_name")
    @ApiModelProperty(value="用户名")
    private String userName;

    @TableField(value="nick_name")
    @ApiModelProperty(value="昵称")
    private String nickName;

    @TableField(value="password")
    @ApiModelProperty(value="密码")
    private String password;
    
    @TableField(value="type")
    @ApiModelProperty(value="用户类型：0代表普通用户，1代表管理员")
    private String type;
    
    @TableField(value="status")
    @ApiModelProperty(value="账号状态（0正常 1停用）")
    private String status;
    
    @TableField(value="email")
    @ApiModelProperty(value="邮箱")
    private String email;
    
    @TableField(value="phonenumber")
    @ApiModelProperty(value="手机号")
    private String phonenumber;
    
    @TableField(value="sex")
    @ApiModelProperty(value="用户性别（0男，1女，2未知）")
    private String sex;
    
    @TableField(value="avatar")
    @ApiModelProperty(value="头像")
    private String avatar;
    
    @TableField(value="create_by", fill= FieldFill.INSERT)
    @ApiModelProperty(value="创建人的用户id")
    private Long createBy;
    
    @TableField(value="create_time", fill=FieldFill.INSERT)
    @ApiModelProperty(value="创建时间")
    private Date createTime;
    
    @TableField(value="update_by", fill= FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="更新人")
    private Long updateBy;
    
    @TableField(value="update_time", fill=FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value="更新时间")
    private Date updateTime;
    
    @TableField(value="del_flag")
    @ApiModelProperty(value="删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;
    



}

