package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.CategoryConstant;
import com.example.constant.MethodConstant;
import com.example.domain.dto.CategoryAddDto;
import com.example.domain.dto.CategoryUpdateDto;
import com.example.domain.entity.Category;
import com.example.domain.vo.CategoryVo;
import com.example.mapper.CategoryMapper;
import com.example.service.CategoryService;
import com.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author Zhi
 * @since 2023-03-23 14:29:16
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;
    public CategoryMapper setCategoryMapper() {
        return categoryMapper;
    }

    public List<CategoryVo> getCategoryList() {
        List<Category> categoryList = categoryMapper.getCategoryList();
        List<CategoryVo> categoryVoList = BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class);
        return categoryVoList;
    }

    public List<CategoryVo> getAllCategory() {
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Category::getStatus, CategoryConstant.CATEGORY_STATUS_IS_NORMAL);
        List<Category> categoryList = list(lqw);
        List<CategoryVo> categoryVoList = BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class).stream().map(
          category -> {
              category.setPid(null);
              //category.setStatus(null);
              return category;
          }
        ).collect(Collectors.toList());
        return categoryVoList;
    }

    @Override
    public PageResult<CategoryVo> allCategoryPage(PageParams pageParams, String name, String status) {
        LambdaQueryWrapper<Category> lqw = new LambdaQueryWrapper<>();
        if (StringUtils.hasText(name)){
            lqw.like(Category::getName, name);
        }
        if (StringUtils.hasText(status)) {
            lqw.eq(Category::getStatus, status);
        }
        lqw.eq(Category::getStatus, CategoryConstant.CATEGORY_STATUS_IS_NORMAL);
        Page<Category> page = new Page(pageParams.getPageNum(), pageParams.getPageSize());
        List<Category> categoryList = page(page, lqw).getRecords();

        List<CategoryVo> categoryVoList = BeanCopyUtils.copyBeanList(categoryList, CategoryVo.class);
        PageResult<CategoryVo> categoryPageResult = new PageResult<>(categoryVoList, page.getTotal(), pageParams);
        return categoryPageResult;
    }

    @Override
    public CategoryVo getCategoryById(Long id) {
        Category category = categoryMapper.selectById(id);
        CategoryVo categoryVo = BeanCopyUtils.copyBean(category, CategoryVo.class);
        return categoryVo;
    }

    @Override
    public int addCategory(CategoryAddDto categoryAddDto) {
        Category category = BeanCopyUtils.copyBean(categoryAddDto, Category.class);
        int i = categoryMapper.insert(category);
        return MethodConstant.ERROR != i ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }

    @Override
    public int deleteCategoryById(Long id) {
        int i = categoryMapper.deleteById(id);
        return MethodConstant.ERROR != i ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }

    @Override
    public int updateCategory(CategoryUpdateDto categoryUpdateDto) {
        Category category = BeanCopyUtils.copyBean(categoryUpdateDto, Category.class);
        int i = categoryMapper.updateById(category);
        return MethodConstant.ERROR != i ? MethodConstant.SUCCESS:MethodConstant.ERROR;
    }
}
