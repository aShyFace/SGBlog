package com.example.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @ClassName: LinkVo
 * @Description:
 * @author: Zhi
 * @date: 2023/3/29 下午8:50
 */
@AllArgsConstructor
@NoArgsConstructor
@Repository
@Data
public class LinkVo {
    private Long id;

    private String name;

    private String logo;

    private String address;

    private String description;

//    private String status;
//
//    private Long createBy;
//
//    private Date createTime;
//
//    private Long updateBy;
//
//    private Date updateTime;
//
//    private Integer delFlag;
}
