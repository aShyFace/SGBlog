package com.example.domain.entity;


import java.util.Date;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * 评论表(Comment)表实体类
 *
 * @author Zhi
 * @since 2023-04-07 14:05:03
 */
@TableName(value="sg_comment")
@ApiModel(value="Comment对象", description="评论表")
@SuppressWarnings(value={"serial", "unused"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class Comment {
    @TableId(value="id")
    @ApiModelProperty(value="")
    private Long id;


   @TableField(value="type")
    @ApiModelProperty(value="评论类型（0代表文章评论，1代表友链评论）")
    private String type;
    
   @TableField(value="article_id")
    @ApiModelProperty(value="文章id")
    private Long articleId;
    
   @TableField(value="root_id")
    @ApiModelProperty(value="根评论id")
    private Long rootId;
    
   @TableField(value="content")
    @ApiModelProperty(value="评论内容")
    private String content;
    
   @TableField(value="to_comment_user_id")
    @ApiModelProperty(value="所回复的目标评论的userid")
    private Long toCommentUserId;
    
   @TableField(value="to_comment_id")
    @ApiModelProperty(value="回复目标评论id")
    private Long toCommentId;
    
   @TableField(value="create_by")
    @ApiModelProperty(value="")
    private Long createBy;
    
   @TableField(value="create_time")
    @ApiModelProperty(value="")
    private Date createTime;
    
   @TableField(value="update_by")
    @ApiModelProperty(value="")
    private Long updateBy;
    
   @TableField(value="update_time")
    @ApiModelProperty(value="")
    private Date updateTime;
    
   @TableField(value="del_flag")
    @ApiModelProperty(value="删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;
    



}

