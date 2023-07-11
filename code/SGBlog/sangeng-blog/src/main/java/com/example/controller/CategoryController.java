package com.example.controller;




import com.example.domain.ResponseResult;
import com.example.domain.vo.CategoryVo;
import com.example.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类表(Category)表控制层
 *
 * @author Zhi
 * @since 2023-03-23 15:15:14
 */
@Slf4j
@Validated
@RestController
@Api(tags = "文章类别相关接口")
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    /**
     * 服务对象
     */
    @Resource
    private CategoryService categoryService;

    @GetMapping("/getCategoryList")
    @ApiOperation(value = "返回所有已发表文章的所属分类")
    public ResponseResult<List<CategoryVo>> getCategoryList(){
        List<CategoryVo> categoryVoList = categoryService.getCategoryList();
        return ResponseResult.okResult(categoryVoList);
    }

}

