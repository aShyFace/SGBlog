package com.example.controller;

import com.example.constant.UploadFileConstant;
import com.example.domain.ResponseResult;
import com.example.enums.AppHttpCodeEnum;
import com.example.exception.SystemException;
import com.example.service.impl.UploadServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * @ClassName: UploadController
 * @Description:
 * @author: Zhi
 * @date: 2023/4/13 下午2:46
 */
@Slf4j
@Validated
@RestController
@Api(tags = "上传接口")
public class UploadController {
    @Resource
    private UploadServiceImpl uploadService;

    @PostMapping("/upload")
    @ApiOperation(value = "上传文件至静态资源服务器")
    /* 与@Getmapping一样，方法的参数名要和from表单中的key一致，否则该参数的值为null。文件类型的数据，可以不用添加@Requestbody */
    public ResponseResult uploadFile(@NotNull MultipartFile img){
        MultipartFile file = img;
        String redictUrl = uploadService.uploadFile(file);
        return ResponseResult.okResult(redictUrl);
    }
}
