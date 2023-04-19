package com.example.controller;




import com.example.domain.ResponseResult;
import com.example.domain.vo.LinkVo;
import com.example.domain.entity.Link;
import com.example.service.LinkService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
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
@RequestMapping("link")
public class LinkController {
    /**
     * 服务对象
     */
    @Resource
    private LinkService linkService;

    @GetMapping("getAllLink")
    @ApiOperation(value = "返回所有已审核通过的友链")
    private ResponseResult<LinkVo> getAllLink(){
        List<LinkVo> linkVoList = linkService.getAllLink();
        return ResponseResult.okResult(linkVoList);
    }
}

