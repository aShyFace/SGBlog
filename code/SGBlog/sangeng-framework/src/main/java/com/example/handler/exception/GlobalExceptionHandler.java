package com.example.handler.exception;

import com.alibaba.excel.util.StringUtils;
import com.example.domain.ResponseResult;
import com.example.exception.SystemException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

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
@RestControllerAdvice // 通知类
public class GlobalExceptionHandler {

    // 自定义的ResponseResult中添加了JsonInclude注解，它会自动把返回值封装成json。所以这里不需要添加ReponseBody注解
    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException systemException){
        log.error("|||||||||||||||\n捕获SystemException异常!!!\n|||||||||||||||\n" + systemException.getMsg());
        return ResponseResult.errorResult(systemException.getCode(), systemException.getMsg());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult mthodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        ArrayList<String> errorList = new ArrayList<>();
        // stream() 调用的是有返回值的方法，就用map()；是无返回值的方法，就用forEach()
        bindingResult.getFieldErrors().stream().forEach(fieldError -> {
            errorList.add(fieldError.getDefaultMessage());
        });

        String error = String.join("; \n", errorList);
        log.error("|||||||||||||||\n参数异常!!!\n|||||||||||||||\n" + error);
        return ResponseResult.errorResult(400, error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseResult systemExceptionHandler(Exception e){
        log.error("|||||||||||||||\n捕获Exception异常!!!\n|||||||||||||||\n" + e.getMessage());
        return ResponseResult.errorResult(500, e.getMessage());
    }
}
