package com.example.controller;


import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.MethodConstant;
import com.example.domain.ResponseResult;
import com.example.domain.dto.AddArticleDto;
import com.example.domain.vo.article.ArticleManagerVo;
import com.example.domain.vo.article.ArticleUpdateVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.handler.exception.ValidationGroups;
import com.example.service.ArticleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

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
//@CrossOrigin(origins = "*")
public class ArticleController {
    /**
     * 服务对象
     */
    @Resource
    private ArticleService articleService;


    @PostMapping("")
    public ResponseResult addArticle(@Validated(value = ValidationGroups.ArticleInsert.class)
                                     @RequestBody AddArticleDto addArticleDto){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(),
            addArticleDto.toString());
        int ret = articleService.addArticle(addArticleDto);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
        }
    }

    @PutMapping("")
    public ResponseResult updateArticle(@RequestBody AddArticleDto addArticleDto){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(),
          addArticleDto.toString());
        int ret = articleService.updateArticle(addArticleDto);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseResult getArticleById(@Min(1L) @PathVariable Long id){
        ArticleUpdateVo articleUpdateVo = articleService.getUpdateArticleById(id);
        return ResponseResult.okResult(articleUpdateVo);
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteArticleById(@Min(1L) @PathVariable Long id){
        int ret = articleService.deleteArticleById(id);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DELETE_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseResult allArticleList(@Validated(value = ValidationGroups.PageParams.class)
                                         PageParams pageParams, String title, String summary){
        log.debug("||||| {}::{}, {}, {} |||||", new Exception().getStackTrace()[0].getMethodName(), pageParams.toString(), title, summary);
        PageResult<ArticleManagerVo> articleManagerVoList = articleService.allArticleList(pageParams, title, summary);
        return ResponseResult.okResult(articleManagerVoList);
    }


}

