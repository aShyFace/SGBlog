package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.ArticleMapper;
import com.example.entity.Article;
import com.example.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * 文章表(Article)表服务实现类
 *
 * @author Zhi
 * @since 2023-02-20 23:02:26
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
