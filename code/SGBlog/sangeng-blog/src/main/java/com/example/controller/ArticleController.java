package com.example.controller;



import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.domain.dto.HotArticleDto;
import com.example.domain.ResponseResult;
import com.example.domain.entity.Article;
import com.example.domain.vo.ArticleVo;
import com.example.handler.exception.ValidationGroups;
import com.example.service.ArticleService;
import com.example.utils.BeanCopyUilts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 文章表(Article)表控制层
 *
 * @author Zhi
 * @since 2023-02-21 13:48:02
 */
@RestController //告诉springMvc，别把controller的返回值当作modelView解析，而是把它写入响应体中
@Api(tags = "文章相关接口")
@RequestMapping("/article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource //按名称注入
    private ArticleService articleService;

    @GetMapping("/all")
    @ApiOperation(value = "返回所有已发表文章")
    public ResponseResult<List<Article>> test(){
        return ResponseResult.okResult(articleService.list());
    }

    @GetMapping("/hotArticleList")
    @ApiOperation(value = "返回浏览量前10的 已发表的文章（也就是热门文章）")
    public ResponseResult<List<HotArticleDto>> hotArticleList(){
        List<HotArticleDto> data =  articleService.hotArticleList();
        return ResponseResult.okResult(data);
    }

    @GetMapping("articleList")
    @ApiOperation(value = "根据文章类别返回对应的文章，需要设置分页参数", notes = "参数为 pageNum，pageSize，categoryId")
    public ResponseResult<List<ArticleVo>> articleList(@Validated(value = ValidationGroups.PageParams.class) PageParams pageParams, Long categoryId){
        PageResult<ArticleVo> pageArticle = articleService.articleList(pageParams, categoryId);
        return ResponseResult.okResult(pageArticle);
    }

    @GetMapping("{articleId}")
    @ApiOperation(value = "根据传入的articleId返回对应文章")
    public ResponseResult<ArticleVo> getArticleById(@Validated(value = ValidationGroups.ArticleQuery.class) @PathVariable(value = "articleId") Long articleId){
        ArticleVo articleVo = articleService.getArticleById(articleId);
        return ResponseResult.okResult(articleVo);
    }
}

