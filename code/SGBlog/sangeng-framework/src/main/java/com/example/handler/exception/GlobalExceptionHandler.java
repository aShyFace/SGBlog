package com.example.handler.exception;

import com.example.domain.ResponseResult;
import com.example.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 自定义异常处理器，其原理大致为 加了该注解的类会变成通知类：
 *     - RestControllerAdvice：原本是controller抛出异常 --> spring框架捕获并打印异常。
 *          添加该注解后变成，controller抛出异常 --> 添加了该注解的类捕获并处理异常。
 *     - ExceptionHandler：统一处理某种类型的异常，传入异常的class来指定处理哪种异常。
 * @author: Zhi
 * @date: 2023/4/5 上午10:06
 */
@Slf4j
// 通知类
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException systemException){
        log.error("controller出现异常！  ", systemException.getMsg());
        return ResponseResult.errorResult(systemException.getCode(), systemException.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult systemExceptionHandler(Exception exception){
        log.error("controller出现异常！  ", exception.getMessage());
        return ResponseResult.errorResult(500, exception.getMessage());
    }
}
