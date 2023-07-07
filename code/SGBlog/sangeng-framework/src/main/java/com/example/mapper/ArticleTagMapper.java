package com.example.mapper;

import com.example.domain.entity.ArticleTag;
import com.github.jeffreyning.mybatisplus.base.MppBaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-01 16:55:36
 */
@Mapper
public interface ArticleTagMapper extends MppBaseMapper<ArticleTag> {
//public interface ArticleTagMapper extends BaseMapper<ArticleTag> {


}

