package com.example.domain.vo;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * 评论表(Comment)表实体类
 *
 * @author Zhi
 * @since 2023-04-07 14:05:03
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentVo {
    private Long id;

    private Long articleId;

    // 该评论是哪条评论的子评论
    private Long rootId;

    private String content;

    // 该评论回复了哪条评论
    private Long toCommentId;
    // 回复的哪条评论是谁的（用户名）
    private Long toCommentUserId;
    private String toCommentUserName;

    // 该评论是谁发表的
    private Long createBy;
    public String username;

    // 子评论
    private List children;

    private Date createTime;

}