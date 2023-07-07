package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.domain.dto.CategoryAddDto;
import com.example.domain.dto.CategoryUpdateDto;
import com.example.domain.vo.CategoryVo;
import com.example.domain.entity.Category;

import java.util.List;

/**
 * 分类表(Category)表服务接口
 *
 * @author Zhi
 * @since 2023-03-23 14:29:16
 */
public interface CategoryService extends IService<Category> {

  List<CategoryVo> getCategoryList();

  List<CategoryVo> getAllCategory();

  PageResult<CategoryVo> allCategoryPage(PageParams pageParams, String name, String status);

  CategoryVo getCategoryById(Long id);

  int addCategory(CategoryAddDto categoryAddDto);

  int deleteCategoryById(Long id);

  int updateCategory(CategoryUpdateDto categoryUpdateDto);
}

