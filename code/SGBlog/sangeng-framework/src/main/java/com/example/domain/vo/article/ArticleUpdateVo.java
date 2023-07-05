package com.example.domain.vo.article;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@ApiModel(value = "ArticleUpdateVo对象", description = "后台文章更新类")
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class ArticleUpdateVo {
  @ApiModelProperty(value = "id")
  private Long id;

  @ApiModelProperty(value = "标题")
  private String title;

  @ApiModelProperty(value = "文章摘要")
  private String summary;

  @ApiModelProperty(value = "分类id")
  private String categoryId;

  @ApiModelProperty(value = "所属分类")
  private String categoryName;

  @ApiModelProperty(value = "缩略图")
  private String thumbnail;

  @ApiModelProperty(value = "状态（0已发布，1草稿）")
  private String status;

  @ApiModelProperty(value = "访问量")
  private Long viewCount;

  @ApiModelProperty(value = "是否允许评论 1是，0否")
  private String isComment;

  @ApiModelProperty(value = "创建人")
  private Date createBy;

  @ApiModelProperty(value = "创建时间")
  private Date createTime;

  @ApiModelProperty(value = "文章内容")
  private String content;

  @ApiModelProperty(value = "文章内容")
  private List<Long> tags;

  @ApiModelProperty(value = "删除标志（0代表未删除，1代表已删除）")
  private Integer delFlag;
}
