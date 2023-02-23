package com.example.controller;



import com.example.entity.Article;
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
@RestController
@RequestMapping("/article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;

    @RequestMapping("/all")
    public List<Article> test(){
        return articleService.list();
    }
}

