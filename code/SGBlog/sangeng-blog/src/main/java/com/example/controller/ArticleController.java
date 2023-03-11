package com.example.controller;



import com.example.domain.ResponseResult;
import com.example.domain.entity.Article;
import com.example.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 文章表(Article)表控制层
 *
 * @author Zhi
 * @since 2023-02-21 13:48:02
 */
@RestController //告诉springMvc，别把controller的返回值当作modelView解析，而是把它写入响应体中
@RequestMapping("/article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource //按名称注入
    private ArticleService articleService;

    @RequestMapping("/all")
    public List<Article> test(){
        return articleService.list();
    }

    @RequestMapping("/hotArticleList")
    public ResponseResult<Article> hotArticalList(){
        ResponseResult<Article> articalList = articleService.hotArticalList();
        return articalList;
    }
}

