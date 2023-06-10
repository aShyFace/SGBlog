package com.example.domain.vo.article;


import com.example.handler.exception.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Date;


@ApiModel(value="ArticlePreviewVo对象", description="文章预览类")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class ArticlePreviewVo {
    // @ApiModelProperty(value = "文章id")
    @NotNull(message = "文章id不能为空", groups = {ValidationGroups.ArticleQuery.class})
    private Long id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "文章摘要")
    private String summary;

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

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "文章内容")
    private String content;


}
