package com.example.controller;


import com.example.domain.ResponseResult;
import com.example.domain.vo.LinkVo;
import com.example.service.LinkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 友链(Link)表控制层
 *
 * @author Zhi
 * @since 2023-03-29 20:47:10
 */
@Slf4j
@Validated
@RestController
@Api(tags = "友链相关接口")
@RequestMapping("/link")
@CrossOrigin(origins = "*")
public class LinkController {
    /**
     * 服务对象
     */
    @Resource
    private LinkService linkService;

    @GetMapping("/getAllLink")
    @ApiOperation(value = "返回所有已审核通过的友链")
    public ResponseResult<LinkVo> getAllLink(){
        List<LinkVo> linkVoList = linkService.getAllLink();
        return ResponseResult.okResult(linkVoList);
    }
}

