package com.example.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.*;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: OssConfig
 * @Description: 七牛云配置类读取
 * @author: Zhi
 * @date: 2023/4/13 下午3:05
 */
@Configuration
public class OssUtils {
    private static String ACCESSKEY;
    private static String SECREKETKRY;
    private static String BUCKET;

    @Value("${oss.accessKey}")
    public void setAccessKey(String accessKey) {
        ACCESSKEY = accessKey;
    }

    @Value("${oss.secretKey}")
    public void setSecretKey(String secretKey) {
        SECREKETKRY = secretKey;
    }

    @Value("${oss.bucket}")
    public void setBucket(String bucket) {
        BUCKET = bucket;
    }


    public String uploadFile(String fileName, MultipartFile multipartFile){
        //构造一个带指定 Region 对象的配置类
        com.qiniu.storage.Configuration cfg = new com.qiniu.storage.Configuration(Region.region2());
        cfg.resumableUploadAPIVersion = com.qiniu.storage.Configuration.ResumableUploadAPIVersion.V2;// 指定分片上传版本
        //...其他参数参考类注释

        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;
        try {
            // String filePath = "/home/zhi/WeChat_Files/WeChat Files/wxid_grsyuu1kfv3e22/FileStorage/MsgAttach/01c0704314004272e5149c7224a3d216/File/error.html";
            // key = StringUtils.getFilename(filePath);
            // BufferedInputStream inputStream = FileUtil.getInputStream(filePath);
            Auth auth = Auth.create(ACCESSKEY, SECREKETKRY);
            String upToken = auth.uploadToken(BUCKET);

            try {
                Response response = uploadManager.put(multipartFile.getInputStream(), key, upToken,null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println("解析出错： " + r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                    return "UploadServiceImpl::uploadFile QiniuException";
                }
            }
        } catch (Exception e) {
            //ignore
            System.out.println("UploadServiceImpl::uploadFile：");
            e.printStackTrace();
        }
        return "???";
    }

}
