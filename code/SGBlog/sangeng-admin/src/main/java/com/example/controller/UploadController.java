package com.example.controller;


import com.example.domain.ResponseResult;
import com.example.service.UploadService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 *
 *
 * @author Zhi
 * @since 2023-06-28 21:04:07
 */
@Slf4j
@Validated
@RestController
@Api(tags = "文件上传接口")
//@RequestMapping("/content/category")
//@CrossOrigin(origins = "*")
public class UploadController {
  @Resource
  private UploadService uploadService;

  @PostMapping("/upload")
  public ResponseResult uploadImg(@NotNull MultipartFile img) {
    String redictUrl = uploadService.uploadFile(img);
    return ResponseResult.okResult(redictUrl);
  }

}
