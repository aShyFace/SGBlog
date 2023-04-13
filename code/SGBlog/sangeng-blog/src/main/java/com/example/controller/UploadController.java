package com.example.controller;

import com.example.domain.ResponseResult;
import com.example.enums.AppHttpCodeEnum;
import com.example.service.impl.UploadServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @ClassName: UploadController
 * @Description:
 * @author: Zhi
 * @date: 2023/4/13 下午2:46
 */
@RestController
public class UploadController {
    @Resource
    private UploadServiceImpl uploadService;

    @PostMapping("/upload")
    public ResponseResult uploadFile(@RequestBody MultipartFile img){
        if (Objects.isNull(img)){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_IS_NULL);
        }
        String redictUrl = uploadService.uploadFile(img);
        return ResponseResult.okResult(redictUrl);
    }
}
