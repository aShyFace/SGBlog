package com.example.controller;




import com.example.domain.ResponseResult;
import com.example.domain.dto.CategoryDto;
import com.example.domain.entity.Category;
import com.example.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类表(Category)表控制层
 *
 * @author Zhi
 * @since 2023-03-23 15:15:14
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    @GetMapping("getCategoryList")
    @ApiOperation(value = "返回所有已发表文章的所属分类")
    public ResponseResult<List<CategoryDto>> getCategoryList(){
        List<CategoryDto> categoryDtoList = categoryService.getCategoryList();
        return ResponseResult.okResult(categoryDtoList);
    }

}

