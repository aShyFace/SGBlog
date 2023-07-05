package com.example.domain.vo.article;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;


@ApiModel(value = "HotArticleVo对象", description = "热门文章类")
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class HotArticleVo {
    @ApiModelProperty(value = "文章id")
    private Long id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty(value = "浏览量")
    private Long viewCount;

}
