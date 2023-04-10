package com.example.controller;




import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.domain.ResponseResult;
import com.example.domain.entity.Comment;
import com.example.domain.vo.CommentVo;
import com.example.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

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
    public ResponseResult commentList(PageParams pageParams, Long articleId){
        PageResult<CommentVo> commentVoPage = commentService.commentList(pageParams, articleId);
        return ResponseResult.okResult(commentVoPage);
    }
}

