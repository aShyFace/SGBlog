package com.example.domain.dto;

import com.example.handler.exception.ValidationGroups;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

/**
 * 评论表(Comment)表实体类
 *
 * @author Zhi
 * @since 2023-04-07 14:05:03
 */
@ApiModel(value = "添加评论实体类")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddCommentDto {
    @ApiModelProperty(value = "")
    @Null(message = "新增时id必须为空", groups = {ValidationGroups.CommentInsert.class})
    private Long id;

    @ApiModelProperty(value = "评论类型（0代表文章评论，1代表友链评论）")
    @NotBlank(message = "评论类型不能为空", groups = {ValidationGroups.CommentInsert.class})
    @Range(min=0, max=1, message = "评论类型只能为0或1", groups = {ValidationGroups.CommentInsert.class})
    private String type;

    @ApiModelProperty(value = "文章id")
    private Long articleId;

    // 该评论是哪条评论的子评论
    @ApiModelProperty(value = "根评论id")
    private Long rootId;

    @ApiModelProperty(value = "评论内容")
    @NotBlank(message = "评论内容不能为空", groups = {ValidationGroups.CommentInsert.class})
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
    @Null(message = "新增时createBy必须为空", groups = {ValidationGroups.CommentInsert.class})
    private Long createBy;
    @Null(message = "新增时username必须为空", groups = {ValidationGroups.CommentInsert.class})
    @ApiModelProperty(value = "发表该评论的user name")
    public String username;

    // 子评论
    @ApiModelProperty(value = "该评论的子评论")
    @Null(message = "新增时children必须为空", groups = {ValidationGroups.CommentInsert.class})
    private List children;

    @ApiModelProperty(value = "该评论的发表时间")
    @Null(message = "新增时children必须为空", groups = {ValidationGroups.CommentInsert.class})
    private Date createTime;

}