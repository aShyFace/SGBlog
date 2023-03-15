package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.dto.HotArticleDto;
import com.example.mapper.ArticleMapper;
import com.example.domain.entity.Article;
import com.example.service.ArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 文章表(Article)表服务实现类
 *
 * @author Zhi
 * @since 2023-02-20 23:02:26
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    /* 跟接口一样，如果返回值类型为ResponseResult，则代表该方法能返回ResponseResult<各种类型>；
    *       如果返回值类型为ResponseResult<XXX>，则该方法只能返回ResponseResult<XXX>。  */
    public List<HotArticleDto> hotArticleList() {
        // 创建查询对象
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<Article>();
        // 编写sql语句
        lqw.eq(Article::getStatus, 0).orderByDesc(Article::getViewCount);
        IPage page = new Page(1, 10);
        // 执行sql语句
        page(page, lqw);
        List<Article> articleList = page.getRecords();

//        bean拷贝
        List<HotArticleDto> hotArticleDtoList = new ArrayList<>();
        Iterator<Article> articleIterator = articleList.iterator();
        while(articleIterator.hasNext()) {
            HotArticleDto hotArticleDto = new HotArticleDto();
            BeanUtils.copyProperties(articleIterator.next(), hotArticleDto);
            hotArticleDtoList.add(hotArticleDto);
        }
        return hotArticleDtoList;
    }
}
