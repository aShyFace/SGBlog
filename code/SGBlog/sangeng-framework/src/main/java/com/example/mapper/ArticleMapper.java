package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.entity.Article;
import com.example.domain.vo.ArticleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 文章表(Article)表数据库访问层
 *
 * @author Zhi
 * @since 2023-02-21 17:30:58
 */
// 猜测BaseMapper实现了基本的crud，继承了它就不用搞那些简单的sql语句了。  复杂的还是得用mybatis来写
// 想支持mybatis，就得加Mapper注解
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
//    List<ArticleVo> getArticleList(@Param(value = "categoryId") Long categoryId);
}

