﻿import request from '@/utils/request'

// 发送文章评论
export function sendComment(type,articleId,rootId,toCommentId,toCommentUserId,content) {
    // debugger
    return request({
        url: '/comment',
        method: 'post',
        headers: {
            isToken: true
          },
        data: {"articleId":articleId,"type":type,"rootId":rootId,"toCommentId":toCommentId,"toCommentUserId":toCommentUserId,"content":content}
    })
}


export function getArticleComment(query) {
    // debugger
    return request({
        url: '/comment/commentList',
        method: 'get',
        headers: {
          isToken: true
        },
        params: query
    })
}


export function getLinkComment(query) {
    return request({
        url: '/comment/linkCommentList',
        method: 'get',
        headers: {
            isToken: true
          },
        params: query
    })
}