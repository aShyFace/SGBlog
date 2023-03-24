package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.dto.HotArticleDto;
import com.example.domain.ResponseResult;
import com.example.domain.entity.Article;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * 文章表(Article)表服务接口
 *
 * @author Zhi
 * @since 2023-02-20 22:57:14
 */
public interface ArticleService extends IService<Article> {

    List<HotArticleDto> hotArticleList();
}

