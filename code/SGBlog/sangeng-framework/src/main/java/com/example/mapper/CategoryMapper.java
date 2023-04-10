package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 分类表(Category)表数据库访问层
 *
 * @author Zhi
 * @since 2023-03-23 14:29:16
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> getCategoryList();
}

