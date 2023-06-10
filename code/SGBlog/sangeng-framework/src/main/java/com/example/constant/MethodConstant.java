package com.example.constant;

/**
 * @ClassName: MethodConstant
 * @Description: 方法执行状态
 * @author: Zhi
 * @date: 2023/4/20 下午9:13
 */
public class MethodConstant {
    /**
     * 方法执行状态。1表示执行成功，0表示执行时出错
     * 用于表示 插入和更新等没有返回值的方法 的执行状态
     */
    public static final int SUCCESS = 1;
    public static final int ERROR = 0;
}
