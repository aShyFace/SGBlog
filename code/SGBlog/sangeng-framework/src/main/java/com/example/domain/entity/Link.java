package com.example.domain.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
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
 * 友链(Link)表实体类
 *
 * @author Zhi
 * @since 2023-03-29 20:41:53
 */
@TableName(value = "sg_link")
@ApiModel(value = "Link对象", description = "友链")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class Link {
    @TableId(value = "id")
//    @ApiModelProperty(value = "")
    private Long id;


    @TableField(value = "name")
//    @ApiModelProperty(value = "")
    private String name;

    @TableField(value = "logo")
//    @ApiModelProperty(value = "")
    private String logo;

    @TableField(value = "description")
//    @ApiModelProperty(value = "")
    private String description;

    @TableField(value = "address")
//    @ApiModelProperty(value = "网站地址")
    private String address;

    @TableField(value = "status")
//    @ApiModelProperty(value = "审核状态 (0代表审核通过，1代表审核未通过，2代表未审核)")
    private String status;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
//    @ApiModelProperty(value = "")
    private Long createBy;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
//    @ApiModelProperty(value = "")
    private Date createTime;

    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
//    @ApiModelProperty(value = "")
    private Long updateBy;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
//    @ApiModelProperty(value = "")
    private Date updateTime;

    @TableField(value = "del_flag")
//    @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;


}

