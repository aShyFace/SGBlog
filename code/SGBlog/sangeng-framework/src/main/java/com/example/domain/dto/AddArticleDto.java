package com.example.domain.dto;

import com.example.handler.exception.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;


@ApiModel(value = "添加文章实体类")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddArticleDto {
    @ApiModelProperty(value = "")
    @Null(message = "新增时id必须为空", groups = {ValidationGroups.ArticleInsert.class})
    private Long id;

    @ApiModelProperty(value = "标题")
    @NotBlank(message = "文章标题不能为空", groups = {ValidationGroups.ArticleInsert.class})
    private String title;

    @ApiModelProperty(value = "文章摘要")
    private String summary;

    @ApiModelProperty(value = "所属分类")
    @NotBlank(message = "所属分类不能为空", groups = {ValidationGroups.ArticleInsert.class})
    private String categoryId;

    @ApiModelProperty(value = "所属标签")
    @NotBlank(message = "所属标签不能为空", groups = {ValidationGroups.ArticleInsert.class})
    private List<Long> tags;

    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    @ApiModelProperty(value = "状态（0已发布，1草稿）")
    @NotBlank(message = "文章状态不能为空", groups = {ValidationGroups.ArticleInsert.class})
    private String status;

    @ApiModelProperty(value = "是否允许评论 1是，0否")
    private String isComment;

    @ApiModelProperty(value = "是否置顶（0否，1是）")
    private String isTop;

    @ApiModelProperty(value = "文章内容")
    @NotBlank(message = "文章内容不能为空", groups = {ValidationGroups.ArticleInsert.class})
    private String content;
}
