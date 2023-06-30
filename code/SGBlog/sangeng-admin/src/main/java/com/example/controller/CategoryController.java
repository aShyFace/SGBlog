package com.example.controller;


import com.example.domain.ResponseResult;
import com.example.domain.vo.CategoryVo;
import com.example.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类表(Category)表控制层
 *
 * @author Zhi
 * @since 2023-06-28 21:04:07
 */
@Slf4j
@Validated
@RestController
@Api(tags = "登录接口")
@RequestMapping("/content/category")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;


    @GetMapping("/listAllCategory")
    @ApiOperation(value = "返回所有分类")
    public ResponseResult listAllCategory(){
        List<CategoryVo> allCategory = categoryService.getAllCategory();
        return ResponseResult.okResult(allCategory);
    }
}

