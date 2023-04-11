package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.CommentConstant;
import com.example.domain.entity.Comment;
import com.example.domain.vo.CommentVo;

/**
 * 评论表(Comment)表服务接口
 *
 * @author Zhi
 * @since 2023-04-07 14:05:03
 */
public interface CommentService extends IService<Comment> {
    // 之后的业务可能会有多种类型的评论，为了兼容这种场景，只使用articleId作为判断条件是不可取的
    PageResult<CommentVo> commentList(CommentConstant.CommentType commentType, PageParams pageParams, Long articleId);

    boolean addComment(Comment comment);
}

