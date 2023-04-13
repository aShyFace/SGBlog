package com.example.service.impl;


import com.example.utils.OssUtils;
import com.example.service.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @ClassName: UploadServiceImpl
 * @Description:
 * @author: Zhi
 * @date: 2023/4/13 下午2:42
 */
@Service
public class UploadServiceImpl implements UploadService {
    @Resource
    private OssUtils ossUtils;

    public String uploadFile(MultipartFile img){

        String res = ossUtils.uploadFile("test.png", img);
        return res;
    }


}
