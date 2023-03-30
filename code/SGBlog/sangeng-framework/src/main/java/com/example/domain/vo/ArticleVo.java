package com.example.domain.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class ArticleVo {
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
