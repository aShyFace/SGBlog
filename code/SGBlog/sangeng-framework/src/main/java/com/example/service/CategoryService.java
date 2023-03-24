package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.domain.dto.CategoryDto;
import com.example.domain.entity.Category;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author Zhi
 * @since 2023-03-23 14:29:16
 */
public interface CategoryService extends IService<Category> {

    List<CategoryDto> getCategoryList();
}

