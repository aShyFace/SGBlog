package com.example.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.example.domain.ResponseResult;
import com.example.domain.vo.CategoryExcelVo;
import com.example.domain.vo.CategoryVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.service.CategoryService;
import com.example.utils.BeanCopyUtils;
import com.example.utils.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public ResponseResult allCategoryList(){
        List<CategoryVo> allCategory = categoryService.getAllCategory();
        return ResponseResult.okResult(allCategory);
    }

    @GetMapping("/export")
    @ApiOperation(value = "导出所有分类")
    // 调用自定义判断权限的方法。形式为 "对象名（spring容器中存在的）.方法名(方法参数)"
    @PreAuthorize("@menuService.isRoot('content:category:export')")
    public void exportAllCategory(HttpServletRequest request, HttpServletResponse response){
        try {
            String filename =  "分类.xlsx";
            // 设置下载文件的响应头
            WebUtils.setDownLoadHeader(filename, request.getServletContext(), response);
            // 获取需要导出的数据
            List<CategoryVo> allCategory = categoryService.getAllCategory();
            List<CategoryExcelVo> categoryExcelVoList = BeanCopyUtils.copyBeanList(allCategory, CategoryExcelVo.class);
            // 数据写入excel（不管是指定文件名还是outputstream，本质上都是输出内容，只不过一个是输出到本地文件，另一个是数据到缓存）
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).autoCloseStream(Boolean.FALSE).sheet("分类导出")
              .doWrite(categoryExcelVoList);
        } catch (IOException e) {
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
        }

        //List<CategoryVo> allCategory = categoryService.getAllCategory();
        //return ResponseResult.okResult(allCategory);
    }
}

