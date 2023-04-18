package com.example.exception;

import com.example.enums.AppHttpCodeEnum;

/**
 * @ClassName: SystemException
 * @Description: 为了统一处理某种类型的异常，需要自定义异常类型（返回给前端的东西，都需要有个统一的格式）
 * @author: Zhi
 * @date: 2023/4/4 下午8:51
 */
public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

    public SystemException(int code, String msg) {
        this.code =code;
        this.msg = msg;
    }
}
