package com.example.controller;




import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.CommentConstant;
import com.example.domain.ResponseResult;
import com.example.domain.entity.Comment;
import com.example.domain.vo.CommentVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.exception.SystemException;
import com.example.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 评论表(Comment)表控制层
 *
 * @author Zhi
 * @since 2023-04-07 14:06:14
 */
@RestController
@RequestMapping("comment")
public class CommentController {
    /**
     * 服务对象
     */
    @Resource
    private CommentService commentService;

    @GetMapping("/commentList")
    @ApiOperation(value = "根据文章id返回该文章下的评论，需要设置分页参数", notes = "参数为 pageNum，pageSize，categoryId")
    public ResponseResult commentList(PageParams pageParams, Long articleId){
        PageResult<CommentVo> commentVoPage = commentService.commentList(CommentConstant.CommentType.ARTICLE, pageParams, articleId);
        return ResponseResult.okResult(commentVoPage);
    }


    @PostMapping
    @ApiOperation(value = "添加评论")
    public ResponseResult addComment(@RequestBody Comment comment){
        boolean success = false;
        if (StringUtils.hasText(comment.getContent())) {
            success = commentService.addComment(comment);
        }else{
            throw new SystemException(AppHttpCodeEnum.PARAM_IS_NULL);
        }

        if (success) {
            return ResponseResult.okResult();
        }else{
            return ResponseResult.errorResult(500, "CommentController::addComment 插入失败");
        }
    }


    @GetMapping("/linkCommentList")
    @ApiOperation(value = "根据友链id返回该友链下的评论，需要设置分页参数", notes = "参数为 pageNum，pageSize，categoryId")
    public ResponseResult linkCommentList(PageParams pageParams){
        PageResult<CommentVo> commentVoPage = commentService.commentList(CommentConstant.CommentType.LINK, pageParams, null);
        return ResponseResult.okResult(commentVoPage);
    }

}

