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
 * 标签(Tag)表实体类
 *
 * @author Zhi
 * @since 2023-06-20 20:42:13
 */
@TableName(value="sg_tag")
@SuppressWarnings(value={"serial", "unused"})
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class Tag {
    @TableId(value="id") //
    private Long id;


    @TableField(value="name")  //标签名
    private String name;

    @TableField(value="create_by", fill = FieldFill.INSERT)  //
    private Long createBy;

    @TableField(value="create_time", fill = FieldFill.INSERT)  //
    private Date createTime;

    @TableField(value="update_by", fill = FieldFill.INSERT_UPDATE)  //
    private Long updateBy;

    @TableField(value="update_time", fill = FieldFill.INSERT_UPDATE)  //
    private Date updateTime;

    @TableField(value="del_flag")  //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

    @TableField(value="remark")  //备注
    private String remark;

}

