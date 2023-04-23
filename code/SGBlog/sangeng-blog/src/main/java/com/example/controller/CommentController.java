package com.example.controller;


import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.CommentConstant;
import com.example.constant.MethodConstant;
import com.example.domain.ResponseResult;
import com.example.domain.dto.AddCommentDto;
import com.example.domain.entity.Comment;
import com.example.domain.vo.CommentVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.handler.exception.ValidationGroups;
import com.example.service.CommentService;
import com.example.utils.BeanCopyUilts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 评论表(Comment)表控制层
 *
 * @author Zhi
 * @since 2023-04-07 14:06:14
 */
@Slf4j
@Validated
@RestController
@Api(tags = "评论相关接口")
@RequestMapping("comment")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    @ApiOperation(value = "根据文章id返回该文章下的评论，需要设置分页参数", notes = "参数为 pageNum，pageSize，categoryId")
    public ResponseResult commentList(@Validated(value = ValidationGroups.PageParams.class) PageParams pageParams,
                                      @Validated(value = ValidationGroups.ArticleQuery.class) Long articleId){
        PageResult<CommentVo> commentVoPage = commentService.commentList(CommentConstant.CommentType.ARTICLE, pageParams, articleId);
        return ResponseResult.okResult(commentVoPage);
    }


    @PostMapping
    @ApiOperation(value = "添加评论")
    public ResponseResult addComment(@Validated(value = ValidationGroups.CommentInsert.class) @RequestBody AddCommentDto addCommentDto){
        int ret = commentService.addComment(BeanCopyUilts.copyBean(addCommentDto, Comment.class));
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else{
            return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
        }
    }


    @GetMapping("/linkCommentList")
    @ApiOperation(value = "根据友链id返回该友链下的评论，需要设置分页参数", notes = "参数为 pageNum，pageSize，categoryId")
    public ResponseResult linkCommentList(@Validated(value = ValidationGroups.PageParams.class) PageParams pageParams){
        PageResult<CommentVo> commentVoPage = commentService.commentList(CommentConstant.CommentType.LINK, pageParams, null);
        return ResponseResult.okResult(commentVoPage);
    }

}

