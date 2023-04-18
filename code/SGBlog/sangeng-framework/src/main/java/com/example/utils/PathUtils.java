package com.example.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: PathUtils
 * @Description:
 * @author: Zhi
 * @date: 2023/4/14 下午2:42
 */
public class PathUtils {

    public static String generateFilePath(String fileName){
        //根据日期生成路径   2022/1/15/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        //uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 获取文件后缀
        int index = fileName.lastIndexOf(".");
        // 路径拼接
        String fileType = fileName.substring(index);
        return String.join("", datePath,uuid,fileType);
    }
}
