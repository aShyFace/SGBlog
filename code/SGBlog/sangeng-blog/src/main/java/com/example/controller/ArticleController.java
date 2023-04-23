package com.example.controller;


import com.example.annotation.SystemLog;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.MethodConstant;
import com.example.domain.ResponseResult;
import com.example.domain.vo.article.HotArticleVo;
import com.example.domain.vo.article.ArticlePreviewVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.handler.exception.ValidationGroups;
import com.example.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 文章表(Article)表控制层
 *
 * @author Zhi
 * @since 2023-02-21 13:48:02
 */
@Slf4j
@SystemLog
@Validated
@RestController //告诉springMvc，别把controller的返回值当作modelView解析，而是把它写入响应体中
@Api(tags = "文章相关接口")
@RequestMapping("/article")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource //按名称注入
    private ArticleService articleService;

    // @GetMapping("/all")
    // @ApiOperation(value = "返回所有已发表文章")
    // public ResponseResult<List<Article>> test(){
    //     return ResponseResult.okResult(articleService.list());
    // }

    @GetMapping("/hotArticleList")
    @ApiOperation(value = "返回浏览量前10的 已发表的文章（也就是热门文章）")
    public ResponseResult<List<HotArticleVo>> hotArticleList(){
        List<HotArticleVo> hotArticleVoList =  articleService.hotArticleList();
        return ResponseResult.okResult(hotArticleVoList);
    }

    @GetMapping("articleList")
    @ApiOperation(value = "根据文章类别返回对应的文章，需要设置分页参数", notes = "参数为 pageNum，pageSize，categoryId")
    @ApiImplicitParams({
           @ApiImplicitParam(name = "pageNum",value = "页号"),
           @ApiImplicitParam(name = "pageSize",value = "每页大小"),
           @ApiImplicitParam(name = "categoryId",value = "分类id"),
    })
    public ResponseResult<List<ArticlePreviewVo>> articleList(@Validated(value = ValidationGroups.PageParams.class) PageParams pageParams,
                                                              @Valid @NotNull(message = "分类id不能为空") @Min(1L) Long categoryId){
        PageResult<ArticlePreviewVo> pageArticle = articleService.articleList(pageParams, categoryId);
        return ResponseResult.okResult(pageArticle);
    }

    @GetMapping("{articleId}")
    @ApiOperation(value = "根据传入的articleId返回对应文章")
    public ResponseResult<ArticlePreviewVo> getArticleById(@Valid @NotNull(message = "文章id不能为空") @Min(1L) @PathVariable(value = "articleId") Long articleId){
        ArticlePreviewVo articlePreviewVo = articleService.getArticleById(articleId);
        return ResponseResult.okResult(articlePreviewVo);
    }

    @PutMapping("/updateViewCount/{articleId}")
    @ApiOperation(value = "根据传入的articleId刷新浏览量")
    public ResponseResult updateViewCount(@Valid @NotNull(message = "文章id不能为空") @Min(1L) @PathVariable(value = "articleId") Long articleId){
        int res = articleService.updateViewCount(articleId);
        if (MethodConstant.SUCCESS == res){
            return ResponseResult.okResult(AppHttpCodeEnum.SYSTEM_ERROR);
        }
        return ResponseResult.okResult();
    }
}

