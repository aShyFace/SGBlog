package com.example.constant;

/**
 * @ClassName: CommentConstant
 * @Description: 一篇文章具有多条评论，一条评论具有多条回复。立即推：
 *      文章和评论是一对多，评论和回复也是一对多。所以可以使用 class中包涵List属性 的方式解决数据传输
 * @author: Zhi
 * @date: 2023/4/7 下午3:04
 */
public class CommentConstant {
    public static final Long COMMENT_IS_ROOT = -1L;
}
