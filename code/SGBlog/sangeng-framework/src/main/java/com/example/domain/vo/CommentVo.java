package com.example.domain.vo;

import java.util.Date;
import java.util.List;

import com.example.handler.exception.ValidationGroups;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 评论表(Comment)表实体类
 *
 * @author Zhi
 * @since 2023-04-07 14:05:03
 */
@ApiModel(value = "评论展示实体类")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentVo {
    @ApiModelProperty(value = "")
    private Long id;

    @ApiModelProperty(value = "评论类型（0代表文章评论，1代表友链评论）")
    private String type;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    // 该评论是哪条评论的子评论
    @ApiModelProperty(value = "根评论id")
    private Long rootId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    // 该评论回复了哪条评论
    @ApiModelProperty(value = "回复目标评论id")
    private Long toCommentId;

    // 回复的哪条评论是谁的（用户名）
    @ApiModelProperty(value = "所回复的目标评论的user id")
    private Long toCommentUserId;
    @ApiModelProperty(value = "所回复的目标评论的user name")
    private String toCommentUserName;

    // 该评论是谁发表的
    @ApiModelProperty(value = "发表该评论的user id")
    private Long createBy;
    @ApiModelProperty(value = "发表该评论的user name")
    public String username;

    // 子评论
    @ApiModelProperty(value = "该评论的子评论")
    private List children;

    @ApiModelProperty(value = "该评论的发表时间")
    private Date createTime;

}