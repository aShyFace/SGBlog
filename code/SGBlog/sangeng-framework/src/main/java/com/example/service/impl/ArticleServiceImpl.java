package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.ArticleConstantPage;
import com.example.domain.dto.HotArticleDto;
import com.example.domain.entity.Category;
import com.example.domain.vo.ArticleVo;
import com.example.mapper.ArticleMapper;
import com.example.domain.entity.Article;
import com.example.mapper.CategoryMapper;
import com.example.service.ArticleService;
import com.example.utils.BeanCopyUilts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 文章表(Article)表服务实现类
 *
 * @author Zhi
 * @since 2023-02-20 23:02:26
 */
@Slf4j
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;
    @Resource
    private CategoryMapper categoryMapper;

    /* 跟接口一样，如果返回值类型为ResponseResult，则代表该方法能返回ResponseResult<各种类型>；
    *       如果返回值类型为ResponseResult<XXX>，则该方法只能返回ResponseResult<XXX>。  */
    public List<HotArticleDto> hotArticleList() {
//        因为这个需求只要用 分页的第一页数据，所以这里就直接使用MP了，
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getStatus, ArticleConstantPage.ARTICLE_IS_DRAFT);
        lqw.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page(ArticleConstantPage.Hot_ARTICLE_PAGE_BEGIN,ArticleConstantPage.Hot_ARTICLE_PAGE_SIZE);
        page(page,lqw);

        List<Article> articleList = page.getRecords();
        List<HotArticleDto> hotArticleDtoList = BeanCopyUilts.copyBeanList(articleList, HotArticleDto.class);
        return hotArticleDtoList;
    }


    public PageResult<ArticleVo> articleList(PageParams pageParams, Long categoryId) {
//        分页查询
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getStatus, ArticleConstantPage.ARTICLE_IS_DRAFT).orderByDesc(Article::getIsTop);
        Page<Article> page = new Page(pageParams.getPageNum(), pageParams.getPageSize());
        page(page, lqw);
        List<Article> articleList = page.getRecords();

//        把分类名称设置到分页查询结果中
        String categoryName = categoryMapper.selectById(categoryId).getName();
        List<ArticleVo> articleVoList = BeanCopyUilts.copyBeanList(articleList, ArticleVo.class)
                                        .stream().map(articleVo -> {
                                            articleVo.setCategoryName(categoryName);
                                            articleVo.setContent(" ");
                                            return articleVo;
                                        }).collect(Collectors.toList());
        PageResult<ArticleVo> pageResult = new PageResult<>(articleVoList, articleList.size(), pageParams.getPageNum(), pageParams.getPageSize());
        return pageResult;
    }


    public ArticleVo getArticleById(Long articleId) {
        ArticleVo articleVo = BeanCopyUilts.copyBean(articleMapper.selectById(articleId), ArticleVo.class);
        if (log.isDebugEnabled()){
            log.debug("getArticleById::{}", articleVo.toString());
        }
        Category category = categoryMapper.selectById(articleId);
        if (Objects.nonNull(category)){
            articleVo.setCategoryName(category.getName());
        }
        return articleVo;
    }


}
