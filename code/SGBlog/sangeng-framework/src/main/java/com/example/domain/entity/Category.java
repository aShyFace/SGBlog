package com.example.domain.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * 分类表(Category)表实体类
 *
 * @author Zhi
 * @since 2023-03-23 14:16:25
 */
@TableName(value = "sg_category")
@ApiModel(value = "Category对象", description = "分类表")
@SuppressWarnings(value = {"serial", "unused"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class Category {
    @TableId(value = "id")
//    @ApiModelProperty(value = "")
    private Long id;


    @TableField(value = "name")
//    @ApiModelProperty(value = "分类名")
    private String name;

    @TableField(value = "pid")
//    @ApiModelProperty(value = "父分类id，如果没有父分类为-1")
    private Long pid;

    @TableField(value = "description")
//    @ApiModelProperty(value = "描述")
    private String description;

    @TableField(value = "status")
//    @ApiModelProperty(value = "状态0:正常,1禁用")
    private String status;

    @TableField(value = "create_by")
//    @ApiModelProperty(value = "")
    private Long createBy;

    @TableField(value = "create_time")
//    @ApiModelProperty(value = "")
    private Date createTime;

    @TableField(value = "update_by")
//    @ApiModelProperty(value = "")
    private Long updateBy;

    @TableField(value = "update_time")
//    @ApiModelProperty(value = "")
    private Date updateTime;

    @TableField(value = "del_flag")
//    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;


}

