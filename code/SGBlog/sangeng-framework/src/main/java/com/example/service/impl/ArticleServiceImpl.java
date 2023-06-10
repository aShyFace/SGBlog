package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.ArticleConstant;
import com.example.constant.MethodConstant;
import com.example.domain.vo.article.HotArticleVo;
import com.example.domain.entity.Category;
import com.example.domain.vo.article.ArticlePreviewVo;
import com.example.mapper.ArticleMapper;
import com.example.domain.entity.Article;
import com.example.mapper.CategoryMapper;
import com.example.service.ArticleService;
import com.example.utils.BeanCopyUtils;
import com.example.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
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
    @Resource
    private RedisCache redisCache;


    /* 跟接口一样，如果返回值类型为ResponseResult，则代表该方法能返回ResponseResult<各种类型>；
    *       如果返回值类型为ResponseResult<XXX>，则该方法只能返回ResponseResult<XXX>。  */
    public List<HotArticleVo> hotArticleList() {
//        因为这个需求只要用 分页的第一页数据，所以这里就直接使用MP了，
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getStatus, ArticleConstant.ARTICLE_IS_DRAFT);
        lqw.orderByDesc(Article::getViewCount);
        Page<Article> page = new Page(ArticleConstant.Hot_ARTICLE_PAGE_BEGIN, ArticleConstant.Hot_ARTICLE_PAGE_SIZE);
        page(page,lqw);

        List<Article> articleList = page.getRecords();
        List<HotArticleVo> hotArticleVoList = BeanCopyUtils.copyBeanList(articleList, HotArticleVo.class);
        Map<String, Integer> viewCount = redisCache.getCacheMap(ArticleConstant.ARTICLE_VIEWCOUNT_KEY);
        hotArticleVoList.stream().forEach(hotArticleVo -> {
            String key = hotArticleVo.getId().toString();
            hotArticleVo.setViewCount(viewCount.get(key).longValue());
        });
        return hotArticleVoList;
    }


    public PageResult<ArticlePreviewVo> articleList(PageParams pageParams, Long categoryId) {
//        分页查询
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getStatus, ArticleConstant.ARTICLE_IS_DRAFT)
                .eq(Article::getCategoryId, categoryId).orderByDesc(Article::getIsTop);
        Page<Article> page = new Page(pageParams.getPageNum(), pageParams.getPageSize());
        page(page, lqw);
        List<Article> articleList = page.getRecords();

//        把分类名称设置到分页查询结果中
        Map<String, Integer> viewCount = redisCache.getCacheMap(ArticleConstant.ARTICLE_VIEWCOUNT_KEY);
        String categoryName = categoryMapper.selectById(categoryId).getName();
        List<ArticlePreviewVo> articlePreviewVoList = BeanCopyUtils.copyBeanList(articleList, ArticlePreviewVo.class)
                                        .stream().map(articlePreviewVo -> {
                                            articlePreviewVo.setCategoryName(categoryName);
                                            articlePreviewVo.setContent(" "); //热门文章列表不显示内容
                                            String key = articlePreviewVo.getId().toString();
                                            articlePreviewVo.setViewCount(viewCount.get(key).longValue());
                                            return articlePreviewVo;
                                        }).collect(Collectors.toList());
        PageResult<ArticlePreviewVo> pageResult = new PageResult<>(articlePreviewVoList, articleList.size(), pageParams.getPageNum(), pageParams.getPageSize());
        return pageResult;
    }


    public ArticlePreviewVo getArticleById(Long articleId) {
        ArticlePreviewVo articlePreviewVo = BeanCopyUtils.copyBean(articleMapper.selectById(articleId), ArticlePreviewVo.class);
        Integer viewCount = redisCache.getCacheMapValue(ArticleConstant.ARTICLE_VIEWCOUNT_KEY, articleId.toString());
        articlePreviewVo.setViewCount(viewCount.longValue());
        if (log.isDebugEnabled()){
            log.debug("getArticleById::{}", articlePreviewVo.toString());
        }
        Category category = categoryMapper.selectById(articleId);
        if (Objects.nonNull(category)){
            articlePreviewVo.setCategoryName(category.getName());
        }
        return articlePreviewVo;
    }

    @Override
    public int updateViewCount(Long articleId) {
        redisCache.incrementCacheMapValue(ArticleConstant.ARTICLE_VIEWCOUNT_KEY, articleId.toString(), 1L);
        return MethodConstant.SUCCESS;
    }



    // private Long getViewCountFromRedis(Long articleId){
    //     Map<String, Integer> viewCountMap = redisCache.getCacheMap(ArticleConstant.ARTICLE_VIEWCOUNT_KEY);
    //     return viewCountMap.get(articleId.toString()).longValue();
    // }
    // // 由于viewCount的最新数据存到了redis，所以viewCount优先从redis里拿
    // private Map<String, Integer> getViewCountFromRedis(){
    //     return redisCache.getCacheMap(ArticleConstant.ARTICLE_VIEWCOUNT_KEY);
    // }
}
