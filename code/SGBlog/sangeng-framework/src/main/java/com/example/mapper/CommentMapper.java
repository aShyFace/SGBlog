package com.example.mapper;

import com.example.domain.vo.CommentVo;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 评论表(Comment)表数据库访问层
 *
 * @author Zhi
 * @since 2023-04-07 14:05:03
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    List<CommentVo> getRootComment(@Param("commentTpye") int commentTpye, @Param("articleId") Long articleId, @Param("root_flg") Long root_flg);

    List<CommentVo> getSubComment(@Param("commentTpye") int commentTpye, @Param("rootId") Long rootId);
}

