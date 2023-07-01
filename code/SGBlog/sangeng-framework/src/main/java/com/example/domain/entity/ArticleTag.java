package com.example.domain.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * 文章标签关联表(ArticleTag)表实体类
 *
 * @author Zhi
 * @since 2023-07-01 16:54:02
 */
@TableName(value="sg_article_tag")
@SuppressWarnings(value={"serial", "unused"})
@AllArgsConstructor
@NoArgsConstructor
@Data
@Repository
public class ArticleTag {
    //@TableId(value="articleId") //文章id
    private Long articleId;

    //@TableId(value="tagId") //标签id
    private Long tagId;





}

