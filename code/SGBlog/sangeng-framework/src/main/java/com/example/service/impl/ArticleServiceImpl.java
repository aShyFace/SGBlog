package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.ResponseResult;
import com.example.mapper.ArticleMapper;
import com.example.domain.entity.Article;
import com.example.service.ArticleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文章表(Article)表服务实现类
 *
 * @author Zhi
 * @since 2023-02-20 23:02:26
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    public ResponseResult<Article> hotArticalList() {
        // 创建查询对象
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<Article>();
        // 编写sql语句
        lqw.eq(Article::getStatus, 0).orderByDesc(Article::getViewCount);
        IPage page = new Page(1, 10);
        // 执行sql语句
        page(page, lqw);
        List<Article> records = page.getRecords();
        return ResponseResult.okResult(records);
    }
}
