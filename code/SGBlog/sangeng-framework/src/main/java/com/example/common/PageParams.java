package com.example.common;

import com.example.handler.exception.ValidationGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.validation.constraints.NotNull;


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
    @NotNull(message = "分页参数不能为空", groups = {ValidationGroups.PageParams.class})
    private Long pageNum;
    @NotNull(message = "分页参数不能为空", groups = {ValidationGroups.PageParams.class})
    private Long pageSize;
}
