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
 * 文章表(Article)表实体类
 *
 * @author Zhi
 * @since 2023-02-23 13:21:53
 */
@TableName(value="sg_article")
@ApiModel(value="Article对象", description="文章表")
@SuppressWarnings(value={"serial", "unused"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class Article {
    @TableId(value="id")
    @ApiModelProperty(value="")
    private Long id;


   @TableField(value="title")
    @ApiModelProperty(value="标题")
    private String title;
    
   @TableField(value="content")
    @ApiModelProperty(value="文章内容")
    private String content;
    
   @TableField(value="summary")
    @ApiModelProperty(value="文章摘要")
    private String summary;
    
   @TableField(value="category_id")
    @ApiModelProperty(value="所属分类id")
    private Long categoryId;
    
   @TableField(value="thumbnail")
    @ApiModelProperty(value="缩略图")
    private String thumbnail;
    
   @TableField(value="is_top")
    @ApiModelProperty(value="是否置顶（0否，1是）")
    private String isTop;
    
   @TableField(value="status")
    @ApiModelProperty(value="状态（0已发布，1草稿）")
    private String status;
    
   @TableField(value="view_count")
    @ApiModelProperty(value="访问量")
    private Long viewCount;
    
   @TableField(value="is_comment")
    @ApiModelProperty(value="是否允许评论 1是，0否")
    private String isComment;
    
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

