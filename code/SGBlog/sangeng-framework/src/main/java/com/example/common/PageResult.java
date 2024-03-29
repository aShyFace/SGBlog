package com.example.common;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 页面结果
 *
 * @author zhi
 * @ClassName: PageResult
 * @Description: 分页数据封装对象
 * @author: Zhi
 * @date: 2023/3/28 下午5:21
 * @date 2023/06/26
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


    /**
     * 页面结果
     * @param rows       行
     * @param total      总
     * @param pageParams 页面参数
     */
    public PageResult(List<T> rows, long total, PageParams pageParams) {
        this.rows = rows;
        this.total = total;
        this.pageNum = pageParams.getPageNum();
        this.pageSize = pageParams.getPageSize();
    }
}
