package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.domain.vo.article.HotArticleVo;
import com.example.domain.entity.Article;
import com.example.domain.vo.article.ArticlePreviewVo;

import java.util.List;

/**
 * 文章表(Article)表服务接口
 *
 * @author Zhi
 * @since 2023-02-20 22:57:14
 */
public interface ArticleService extends IService<Article> {
    List<HotArticleVo> hotArticleList();

    PageResult<ArticlePreviewVo> articleList(PageParams pageParams, Long categoryId);

    ArticlePreviewVo getArticleById(Long articleId);

    int updateViewCount(Long articleId);
}

