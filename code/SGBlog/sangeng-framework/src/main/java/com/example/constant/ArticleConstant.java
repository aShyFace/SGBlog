package com.example.constant;

public class ArticleConstant {
    /**
     *  redis中浏览量的key
     */
    public static final String ARTICLE_VIEWCOUNT_KEY = "article:viewCount";
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_IS_DRAFT = 0;


    /**
     *  分页起始数据位置
     */
    public static final int Hot_ARTICLE_PAGE_BEGIN = 1;
    /**
     *  每页数据条数
     */
    public static final int Hot_ARTICLE_PAGE_SIZE = 10;
    /**
     *  升序或降序
     */
    public static final String Hot_ARTICLE_PAGE_ORDER = "desc";
    /**
     *  按照哪些字段排序
     */
    public enum OrderByColumn{
        ID("id"),
        VIEW_COUNT("view_count"),
        CREATE_TIME("create_time"),
        UPDATE_TIME("update_time"),
        CATEGORY_ID("category_id");

        private final String field;
        OrderByColumn (String field){
            this.field = field;
        }
        public String getField() {
            return field;
        }
    }
}
