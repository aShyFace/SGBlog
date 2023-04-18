package com.example.domain.vo;


import java.util.Date;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户表(User)表实体类
 *
 * @author Zhi
 * @since 2023-03-30 11:13:05
 */
@TableName(value="sys_user")
@ApiModel(value="User对象", description="用户表")
@SuppressWarnings(value={"serial", "unused"})
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class UserVo {
    private String userName;

    private String nickName;

    private String email;

    private String sex;

    private String avatar;

}

