package com.example.runner;

import com.example.constant.ArticleConstant;
import com.example.domain.entity.Article;
import com.example.service.ArticleService;
import com.example.utils.RedisCache;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName: RedisRunner
 * @Description: redis初始化
 * @author: Zhi
 * @date: 2023/4/20 下午1:50
 */
@Component
public class RedisRunner implements CommandLineRunner {
    @Resource
    private RedisCache redisCache;
    @Resource
    private ArticleService articleService;

    @Override
    public void run(String... args) throws Exception {
        articleViewCount();
    }

    public void articleViewCount(){
        List<Article> articleList = articleService.list();
        Map<String, Integer> viewCountMap = articleList.stream().collect(Collectors.toMap(
                // 文章数量有可能超过int能存储的最大值
                article -> article.getId().toString(),
                // long类型存入redis会带“L”，所以要转int
                article -> article.getViewCount().intValue()
        ));
        redisCache.deleteObject(ArticleConstant.ARTICLE_VIEWCOUNT_KEY);
        redisCache.setCacheMap(ArticleConstant.ARTICLE_VIEWCOUNT_KEY, viewCountMap);
    }
}
