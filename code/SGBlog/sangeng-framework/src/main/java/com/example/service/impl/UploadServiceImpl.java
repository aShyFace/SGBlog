package com.example.service.impl;


import com.example.constant.UploadFileConstant;
import com.example.enums.AppHttpCodeEnum;
import com.example.exception.SystemException;
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

    public String uploadFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        String[] str = filename.split("\\.");
        if (! UploadFileConstant.IMG_SUFFIX.contains(str[str.length - 1])){
            throw new SystemException(AppHttpCodeEnum.FILE_TYPE_ERROR);
        }
        // 返回外链
        String res = ossUtils.uploadFile(filename, file);
        return res;
    }


}
