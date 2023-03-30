package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.domain.dto.HotArticleDto;
import com.example.domain.entity.Article;
import com.example.domain.vo.ArticleVo;

import java.util.List;

/**
 * 文章表(Article)表服务接口
 *
 * @author Zhi
 * @since 2023-02-20 22:57:14
 */
public interface ArticleService extends IService<Article> {
    List<HotArticleDto> hotArticleList();

    PageResult<ArticleVo> articleList(PageParams pageParams, Long categoryId);

    ArticleVo getArticleById(Long articleId);
}

