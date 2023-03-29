package com.example.common;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @ClassName: PageResult
 * @Description: 分页数据封装对象
 * @author: Zhi
 * @date: 2023/3/28 下午5:21
 */
@Data
@ToString
public class PageResult<T> {
    // 数据列表
    private List<T> rows;
    //总记录数
    private long total;
    //当前页码
    private long pageNum;
    //每页记录数
    private long pageSize;

    public PageResult(List<T> rows, long total, long pageNum, long pageSize) {
        this.rows = rows;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }
}
