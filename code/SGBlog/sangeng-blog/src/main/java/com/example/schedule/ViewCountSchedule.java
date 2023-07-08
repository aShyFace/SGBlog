package com.example.schedule;

import com.example.constant.ArticleConstant;
import com.example.domain.entity.Article;
import com.example.service.ArticleService;
import com.example.utils.RedisCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @ClassName: ViewCountSchedule
 * @Description: 文章浏览量定时更新任务
 * @author: Zhi
 * @date: 2023/4/20 下午3:56
 */
@Component
public class ViewCountSchedule {
    @Resource
    private RedisCache redisCache;
    @Resource
    private ArticleService articleService;

    @Scheduled(cron = "0/600 * * * * ?")
    public void UpdateViewCount(){
        Map<String, Integer> viewCountMap = redisCache.getCacheMap(ArticleConstant.ARTICLE_VIEWCOUNT_KEY);
        List<Article> articleList = viewCountMap.entrySet().stream().map(
                entity -> new Article(Long.valueOf(entity.getKey()), entity.getValue().longValue())
        ).collect(Collectors.toList());
        if (Objects.nonNull(articleList)){
            // 可在domain中 使用注解中的属性设置为null的字段是否更新，默认不更新为null的字段
            articleService.updateBatchById(articleList);
        }
    }
}
