package com.example.controller;


import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.MethodConstant;
import com.example.domain.ResponseResult;
import com.example.domain.dto.TagDto;
import com.example.domain.vo.TagVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.handler.exception.ValidationGroups;
import com.example.service.TagService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 标签(Tag)表控制层
 *
 * @author Zhi
 * @since 2023-06-20 19:34:45
 */
@Slf4j
@Validated
@RestController
@Api(tags = "登录接口")
@RequestMapping("/content/tag")
public class TagController {
    @Resource
    private TagService tagService;


    @GetMapping("/list")
    public ResponseResult tagList(@Validated(value = ValidationGroups.PageParams.class) PageParams pageParams,
                                  TagDto tagDto){
        log.debug("||||| tagList::{}, {} |||||", tagDto.getName(), tagDto.getRemark());
        PageResult<TagVo> tagPage = tagService.tagList(pageParams, tagDto.getName(), tagDto.getRemark());
        return ResponseResult.okResult(tagPage);
    }

    @PostMapping("")
    public ResponseResult addTag(@Validated(value = ValidationGroups.TagInsert.class)
                                 @RequestBody TagDto tagDto){
        log.debug("||||| tagList::{}, {} |||||", tagDto.getName(), tagDto.getRemark());
        int status = tagService.addTag(tagDto);
        if (MethodConstant.SUCCESS == status) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
        }
    }
}

