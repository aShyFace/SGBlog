package com.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.domain.vo.CategoryVo;
import com.example.domain.entity.Category;
import com.example.mapper.CategoryMapper;
import com.example.service.CategoryService;
import com.example.utils.BeanCopyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
}
