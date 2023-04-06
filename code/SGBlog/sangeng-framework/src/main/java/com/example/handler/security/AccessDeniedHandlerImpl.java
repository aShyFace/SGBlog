package com.example.handler.security;

import com.alibaba.fastjson.JSON;
import com.example.domain.ResponseResult;
import com.example.enums.AppHttpCodeEnum;
import com.example.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: AccessDeniedHandlerImpl
 * @Description: security 根据过滤器链中抛出的异常类型，
 *      把不同的异常交给不同的异常处理器，从而调用我们实现的异常处理方法
 * @author: Zhi
 * @date: 2023/4/4 下午4:30
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        accessDeniedException.printStackTrace();
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        //响应给前端
        WebUtils.renderString(response, JSON.toJSONString(result));
    }

}
