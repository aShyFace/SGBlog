package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.ArticleConstant;
import com.example.constant.MethodConstant;
import com.example.domain.dto.AddArticleDto;
import com.example.domain.entity.Article;
import com.example.domain.entity.ArticleTag;
import com.example.domain.entity.Category;
import com.example.domain.vo.article.ArticleManagerVo;
import com.example.domain.vo.article.ArticlePreviewVo;
import com.example.domain.vo.article.ArticleUpdateVo;
import com.example.domain.vo.article.HotArticleVo;
import com.example.mapper.ArticleMapper;
import com.example.mapper.ArticleTagMapper;
import com.example.mapper.CategoryMapper;
import com.example.service.ArticleService;
import com.example.service.ArticleTagService;
import com.example.utils.BeanCopyUtils;
import com.example.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    private ArticleTagService articleTagService;
    @Resource
    private ArticleTagMapper articleTagMapper;
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
            if (Objects.nonNull(viewCount.get(key))) {
                hotArticleVo.setViewCount(viewCount.get(key).longValue());
            }else{
                hotArticleVo.setViewCount(0L);
            }

        });
        return hotArticleVoList;
    }


    public PageResult<ArticlePreviewVo> articleList(PageParams pageParams, Long categoryId) {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getStatus, ArticleConstant.ARTICLE_IS_DRAFT).orderByDesc(Article::getIsTop);
        // 查询分类id
        List<Category> categoryList = null;
        if (!categoryId.equals(0L)){
            lqw.eq(Article::getCategoryId, categoryId);
            categoryList = categoryMapper.selectList(new LambdaQueryWrapper<Category>().eq(Category::getId, categoryId));
        }else {
            categoryList = categoryMapper.selectList(null);
        }
        Map<Long, String> categoryMap = categoryList.stream().collect(Collectors.toMap(
          Category::getId, Category::getName, (value1, value2) -> value1
        ));

        Page<Article> page = new Page(pageParams.getPageNum(), pageParams.getPageSize());
        page(page, lqw);
        List<Article> articleList = page.getRecords();
        // 把分类名称设置到分页查询结果中
        Map<String, Integer> viewCount = redisCache.getCacheMap(ArticleConstant.ARTICLE_VIEWCOUNT_KEY);
        List<ArticlePreviewVo> articlePreviewVoList = BeanCopyUtils.copyBeanList(articleList, ArticlePreviewVo.class)
                                        .stream().map(apv -> {
                                            apv.setCategoryName(categoryMap.get(apv.getCategoryId()));
                                            apv.setContent(" "); //热门文章列表不显示内容
                                            String key = apv.getId().toString();
                                            if (Objects.nonNull(viewCount.get(key))) {
                                                apv.setViewCount(viewCount.get(key).longValue());
                                            }else{
                                                apv.setViewCount(0L);
                                            }
                                            return apv;
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

    public int updateViewCount(Long articleId) {
        redisCache.incrementCacheMapValue(ArticleConstant.ARTICLE_VIEWCOUNT_KEY, articleId.toString(), 1L);
        return MethodConstant.SUCCESS;
    }


    @Transactional
    public int updateArticle(AddArticleDto addArticleDto) {
        Article article = BeanCopyUtils.copyBean(addArticleDto, Article.class);
        int i = articleMapper.updateById(article);
        Long articleId = article.getId();

        List<ArticleTag> articleTagList = addArticleDto.getTags().stream().map(
          tagId -> new ArticleTag(articleId, tagId)
        ).collect(Collectors.toList());
        articleTagService.saveOrUpdateBatchByMultiId(articleTagList);
        return MethodConstant.ERROR != i ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }

    @Transactional
    public int addArticle(AddArticleDto addArticleDto) {
        Article article = BeanCopyUtils.copyBean(addArticleDto, Article.class);
        int i = articleMapper.insert(article);
        Long articleId = article.getId();

        List<ArticleTag> articleTagList = addArticleDto.getTags().stream().map(
          tagId -> new ArticleTag(articleId, tagId)
        ).collect(Collectors.toList());
        articleTagService.saveBatch(articleTagList);
        return MethodConstant.ERROR != i ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }

    public PageResult<ArticleManagerVo> allArticleList(PageParams pageParams, String title, String summary) {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper();
        if (Objects.nonNull(title)){
            lqw.eq(Article::getTitle, title);
        }
        if (StringUtils.hasText(summary)) {
            lqw.eq(Article::getSummary , summary);
        }

        Page page = new Page(pageParams.getPageNum(), pageParams.getPageSize());
        List<Article> articleList = page(page, lqw).getRecords();
        List<ArticleManagerVo> articleManagerVoList = BeanCopyUtils.copyBeanList(articleList, ArticleManagerVo.class);
        return new PageResult<>(articleManagerVoList, page.getTotal(), pageParams);
    }

    @Transactional
    public ArticleUpdateVo getUpdateArticleById(Long id) {
        Article article = articleMapper.selectById(id);
        ArticleUpdateVo articleUpdateVo = BeanCopyUtils.copyBean(article, ArticleUpdateVo.class);
        //String categoryName = categoryMapper.selectById(article.getCategoryId()).getName();
        //articleUpdateVo.setCategoryName(categoryName);
        List<ArticleTag> articleTagList = articleTagService.list(new LambdaQueryWrapper<ArticleTag>().eq(ArticleTag::getArticleId, article.getId()));
        List<Long> tagList = articleTagList.stream().map(articleTag -> articleTag.getTagId()).collect(Collectors.toList());
        return articleUpdateVo.setTags(tagList);
    }

    @Transactional
    public int deleteArticleById(Long id) {
        LambdaQueryWrapper<Article> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Article::getId, id);
        if (count(lqw) > 0) {
            articleMapper.deleteById(id);
            LambdaQueryWrapper<ArticleTag> queryWrapper = new LambdaQueryWrapper<>();
            articleTagService.getBaseMapper().delete(queryWrapper.eq(ArticleTag::getArticleId, id));
        }
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
