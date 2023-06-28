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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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
        log.debug("||||| addTag::{}, {} |||||", tagDto.getName(), tagDto.getRemark());
        int status = tagService.addTag(tagDto);
        if (MethodConstant.SUCCESS == status) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
        }
    }
    @PutMapping("")
    public ResponseResult updateTag(@Validated(value = ValidationGroups.TagUpdate.class)
                                 @RequestBody TagDto tagDto){
        log.debug("||||| updateTag::{}, {} |||||", tagDto.getName(), tagDto.getRemark());
        int status = tagService.updateTag(tagDto);
        if (MethodConstant.SUCCESS == status) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.UPDATE_ERROR);
        }
    }
    @DeleteMapping("{tagId}")
    public ResponseResult deleteTag(@NotNull(message = "标签id不能为空") @Min(1L)
                                    @PathVariable Long tagId){
        log.debug("||||| deleteTag::{} |||||", tagId);
        int status = tagService.deleteTag(tagId);
        if (MethodConstant.SUCCESS == status) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.INSTER_ERROR);
        }
    }
    @GetMapping("{tagId}")
    public ResponseResult updateTag(@NotNull(message = "标签id不能为空") @Min(1L)
                                    @PathVariable Long tagId){
        log.debug("||||| updateTag::{} |||||", tagId);
        TagVo tagVo = tagService.getTagById(tagId);
        if (Objects.nonNull(tagVo)) {
            return ResponseResult.okResult(tagVo);
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.QUERY_ERROR);
        }
    }
}

