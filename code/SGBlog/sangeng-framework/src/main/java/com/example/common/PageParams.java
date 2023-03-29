package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName: PageParams
 * @Description: 分页查询参数封装类
 * @author: Zhi
 * @date: 2023/3/28 下午4:12
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageParams {
    private Long pageNum = 1L;
    private Long pageSize = 10L;
}
