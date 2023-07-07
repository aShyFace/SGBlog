package com.example.controller;


import com.example.common.PageParams;
import com.example.common.PageResult;
import com.example.constant.MethodConstant;
import com.example.domain.ResponseResult;
import com.example.domain.dto.LinkAddDto;
import com.example.domain.dto.LinkStatusDto;
import com.example.domain.dto.LinkUpdateDto;
import com.example.domain.vo.LinkPreviewVo;
import com.example.enums.AppHttpCodeEnum;
import com.example.handler.exception.ValidationGroups;
import com.example.service.LinkService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Min;

/**
 * 友链(Link)表控制层
 *
 * @author Zhi
 * @since 2023-07-07 16:55:56
 */
@Slf4j
@Validated
@RestController
@Api(tags = "登录接口")
@RequestMapping("/content/link")
public class LinkController {
    /**
     * 服务对象
     */
    @Resource
    private LinkService linkService;

    @GetMapping("/list")
    public ResponseResult getLinkPage(@Validated(value = ValidationGroups.PageParams.class)
                                          PageParams pageParams, String name, String status){
        log.debug("||||| {}::{}, {}, {} |||||", new Exception().getStackTrace()[0].getMethodName(),
            pageParams.toString(), name, status);
        PageResult<LinkPreviewVo> linkPreviewVoPageResult = linkService.allLinkPage(pageParams, name, status);
        return ResponseResult.okResult(linkPreviewVoPageResult);
    }

    @GetMapping("/{id}")
    public ResponseResult getLinkById(@Min(1L) @PathVariable Long id){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(), id);
        LinkPreviewVo linkPreviewVo = linkService.getLinkById(id);
        return ResponseResult.okResult(linkPreviewVo);
    }

    @PostMapping("")
    public ResponseResult addLink(@Validated(value = ValidationGroups.LinkInsert.class)
                                      @RequestBody LinkAddDto linkAddDto){
        log.debug("||||| {}::{}, {}, {} |||||", new Exception().getStackTrace()[0].getMethodName(),
            linkAddDto.toString());
        int ret = linkService.addLink(linkAddDto);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DELETE_ERROR);
        }
    }

    @PutMapping("")
    public ResponseResult updateLinkById(@RequestBody LinkUpdateDto linkUpdateDto){
        log.debug("||||| {}::{}, {}, {} |||||", new Exception().getStackTrace()[0].getMethodName(),
            linkUpdateDto.toString());
        int ret = linkService.updateLinkById(linkUpdateDto);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DELETE_ERROR);
        }
    }

    @PutMapping("/changeLinkStatus")
    public ResponseResult updateLinkStatusById(@RequestBody LinkStatusDto linkStatusDto){
        log.debug("||||| {}::{}, {}, {} |||||", new Exception().getStackTrace()[0].getMethodName(),
            linkStatusDto.toString());
        int ret = linkService.updateLinkStatusById(linkStatusDto);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DELETE_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseResult deleteCategoryById(@Min(1L) @PathVariable Long id){
        log.debug("||||| {}::{} |||||", new Exception().getStackTrace()[0].getMethodName(), id);
        int ret = linkService.deleteLinkById(id);
        if (MethodConstant.SUCCESS == ret) {
            return ResponseResult.okResult();
        }else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DELETE_ERROR);
        }
    }
}

