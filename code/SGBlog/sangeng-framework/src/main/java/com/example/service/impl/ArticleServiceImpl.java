package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.Article;
import com.example.service.ArticleService;
import com.example.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

/**
* @author zhi
* @description 针对表【sg_article(文章表)】的数据库操作Service实现
* @createDate 2023-02-15 16:21:28
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{

}




