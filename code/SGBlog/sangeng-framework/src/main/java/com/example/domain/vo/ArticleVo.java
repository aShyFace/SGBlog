package com.example.domain.vo;


import com.example.handler.exception.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class ArticleVo {
    @NotNull(message = "文章id不能为空", groups = {ValidationGroups.ArticleQuery.class})
    private Long id;

    private String title;

    private String summary;

    private String categoryName;

    private String thumbnail;

    private String status;

    private Long viewCount;

    private String isComment;

    private Date createTime;

    private String content;
}
