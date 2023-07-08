package com.example.handler.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * @ClassName: MyMetaObjectHandler
 * @Description: 使用数据库时自动填充类属性
 * @author: Zhi
 * @date: 2023/4/10 下午7:44
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try {
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = -1L; // 表示是自己创建（用户注册时没有userid，所以这里把值设为-1）
        }
        // Long userId = SecurityUtils.getUserId();
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy", userId , metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (Objects.nonNull(SecurityUtils.getLoginUser())){
            this.setFieldValByName("updateTime", new Date(), metaObject);
            Long userId = SecurityUtils.getUserId();
            if (Objects.isNull(userId)){
                userId = -2L; //未登录的游客也可刷新访问量
            }
            //log.error("||||| {}::{} |||||", new Exception().getStackTrace()[0], userId);
            this.setFieldValByName(" ", userId, metaObject);
        }
    }
}
