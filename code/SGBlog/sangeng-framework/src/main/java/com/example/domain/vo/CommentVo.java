package com.example.domain.vo;

import java.util.Date;
import java.util.List;

import com.example.handler.exception.ValidationGroups;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

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
    @Null(message = "新增时id必须为空", groups = {ValidationGroups.CommentInsert.class})
    private Long id;

    @NotBlank(message = "评论类型不能为空", groups = {ValidationGroups.CommentInsert.class})
    @Range(min=0, max=1, message = "评论类型只能为0或1", groups = {ValidationGroups.CommentInsert.class})
    private String type;

    private Long articleId;

    // 该评论是哪条评论的子评论
    private Long rootId;

    @NotBlank(message = "评论内容不能为空", groups = {ValidationGroups.CommentInsert.class})
    private String content;

    // 该评论回复了哪条评论
    private Long toCommentId;
    // 回复的哪条评论是谁的（用户名）
    private Long toCommentUserId;
    private String toCommentUserName;

    // 该评论是谁发表的
    @Null(message = "新增时createBy必须为空", groups = {ValidationGroups.CommentInsert.class})
    private Long createBy;
    @Null(message = "新增时username必须为空", groups = {ValidationGroups.CommentInsert.class})
    public String username;

    // 子评论
    @Null(message = "新增时children必须为空", groups = {ValidationGroups.CommentInsert.class})
    private List children;

    @Null(message = "新增时children必须为空", groups = {ValidationGroups.CommentInsert.class})
    private Date createTime;

}