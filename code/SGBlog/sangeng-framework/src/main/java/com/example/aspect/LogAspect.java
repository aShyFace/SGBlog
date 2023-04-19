package com.example.aspect;


import com.alibaba.fastjson.JSON;
import com.example.utils.BeanCopyUilts;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @ClassName: LogAspect
 * @Description: 自定义注解切面类
 * @author: Zhi
 * @date: 2023/4/19 上午9:47
 */

@Slf4j
@Aspect
@Component
public class LogAspect {
    // @Pointcut("@annotation(com.example.annotation.SystemLog)") //所有添加了@SystemLog注解的方法
    @Pointcut("execution(* com.example.service.*.*(..))")
    public void controllerPt(){}

    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret = null;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed(); //为了不影响原始方法的执行流程，这里不处理异常
            handleAfter(ret);
        }finally {
            // 结束后换行
            log.info("=======End=======" + System.lineSeparator());
        }
        return ret;
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("=======Start=======");
        // 打印请求 URL
        log.info("URL            : {}", request.getRequestURI());
        // // 打印描述信息
        // log.info("BusinessName   : {}", );
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}：：{}", joinPoint.getTarget().getClass().getName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteHost());
        // 打印请求入参
        log.info("Request Args   : {}", joinPoint.getArgs());
    }

    private void handleAfter(Object obj) {
        // 打印出参
        log.info("Response       : {}", JSON.toJSONString(obj));
    }

    // private <T> void getAnnotation(ProceedingJoinPoint joinPoint, Class<T> logClazz) throws NoSuchMethodException {
    //     Class<?> targetCls = joinPoint.getTarget().getClass();
    //     MethodSignature ms = (MethodSignature) joinPoint.getSignature();
    //     //获取目标方法上的注解指定的操作名称
    //     Method targetMethod = targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
    //     Annotation requiredLog = targetMethod.getAnnotation((Class<Annotation>) logClazz);
    //     // String operation = requiredLog.value();
    //     // System.out.println("targetMethod="+targetMethod);
    //     return null;
    // }


}
