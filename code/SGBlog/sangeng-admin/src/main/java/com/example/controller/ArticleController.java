package com.example.controller;


import com.example.constant.MethodConstant;
import com.example.domain.ResponseResult;
import com.example.domain.dto.AddArticleDto;
import com.example.enums.AppHttpCodeEnum;
import com.example.service.ArticleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 文章表(Article)表控制层
 *
 * @author Zhi
 * @since 2023-06-30 10:33:39
 */
@Slf4j
@Validated
@RestController
@Api(tags = "登录接口")
@RequestMapping("/content/article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;


    @PostMapping("")
    public ResponseResult addArticle(@RequestBody AddArticleDto addArticleDto){
        //Article article = BeanCopyUtils.copyBean(addArticleDto, Article.class);
        int ret = articleService.addArticle(addArticleDto);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
        }
    }
}

