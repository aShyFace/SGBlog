package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mapper.ArticleTagMapper;
import com.example.domain.entity.ArticleTag;
import com.example.service.ArticleTagService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * 文章标签关联表(ArticleTag)表服务实现类
 *
 * @author makejava
 * @since 2023-07-01 17:43:54
 */
@Service("articleTagService")
public class ArticleTagServiceImpl extends ServiceImpl<ArticleTagMapper, ArticleTag> implements ArticleTagService {
    @Resource
    private ArticleTagMapper articleTagMapper;
}   

