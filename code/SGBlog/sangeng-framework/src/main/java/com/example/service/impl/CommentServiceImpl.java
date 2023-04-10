package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.CommentConstant;
import com.example.domain.vo.CommentVo;
import com.example.mapper.CommentMapper;
import com.example.domain.entity.Comment;
import com.example.service.CommentService;
import com.example.utils.BeanCopyUilts;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 评论表(Comment)表服务实现类
 *
 * @author Zhi
 * @since 2023-04-07 14:05:03
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    public PageResult<CommentVo> commentList(PageParams pageParams, Long articleId) {
        // 0.分页之前启动PageHelper
        PageHelper.startPage(pageParams.getPageNum().intValue(), pageParams.getPageSize().intValue());
        // 1.查出当前评论的用户名
        //紧跟着的第一个select方法会被分页
        List<CommentVo> rootCommentList = commentMapper.getRootComment(articleId, CommentConstant.COMMENT_IS_ROOT);

        Iterator<CommentVo> rootCommentIterator = rootCommentList.iterator();
        List<CommentVo> subCommentList = null;
        while(rootCommentIterator.hasNext()){
            CommentVo commentVo = rootCommentIterator.next();
            // 2.查出回复该评论的评论
            subCommentList = commentMapper.getSubComment(commentVo.getId());
            // 赋值
            subCommentList = subCommentList.stream().map(subComment -> {
                subComment.setToCommentUserName(commentVo.getUsername());
                return subComment;
            }).collect(Collectors.toList());
            // 3.设置子评论用户信息
            commentVo.setChildren(subCommentList);
        }

        return new PageResult(rootCommentList, rootCommentList.size(), pageParams);
        // List<CommentVo> commentVoList = BeanCopyUilts.copyBeanList(commentList, CommentVo.class);
        // return new PageResult<>(commentVoList, commentVoList.size(), pageParams);
    }
}
