package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.entity.ArticleTag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章标签关联表(ArticleTag)表数据库访问层
 *
 * @author makejava
 * @since 2023-07-01 16:55:36
 */
@Mapper
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

/**
* 批量新增数据（MyBatis原生foreach方法）
*
* @param entities List<ArticleTag> 实例对象列表
* @return 影响行数
*/
int insertBatch(@Param("entities") List<ArticleTag> entities);

/**
* 批量新增或按主键更新数据（MyBatis原生foreach方法）
*
* @param entities List<ArticleTag> 实例对象列表
* @return 影响行数
* @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
*/
int insertOrUpdateBatch(@Param("entities") List<ArticleTag> entities);

}

