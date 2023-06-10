package com.example.aspect;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: LogAspect
 * @Description: 自定义切面类
 * @author: Zhi
 * @date: 2023/4/19 上午9:47
 */

@Slf4j
@Aspect
@Component
public class LogAspect {
    /**
     * @Description: 指明哪些方法需要增强（切入点）
     */
    @Pointcut("@within(com.example.annotation.SystemLog)") //所有添加了@SystemLog注解的方法
    // @Pointcut("execution(* com.example.controller.*.*(..))")
    public void controllerPt(){}



    /**
     * @Description: 被增强的方法 使用了哪些方法（通知）
     */
    @Around("controllerPt()")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object ret = null;
        try {
            handleBefore(joinPoint);
            ret = joinPoint.proceed(); //为了不影响原始方法的执行流程，这里不处理异常
            handleAfter(joinPoint, ret);
        }finally {
            // 结束后换行
            log.info("=======End=======" + System.lineSeparator());
        }
        return ret;
    }

    private void handleBefore(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        StringBuilder logInfo = new StringBuilder();
        logInfo.append(String.format("%s=======Start======= %s", System.lineSeparator(), System.lineSeparator()));
        // 打印请求 URL
        logInfo.append(String.format("URL            : %s %s", request.getRequestURI(), System.lineSeparator()));
        // // 打印描述信息
        // log.info("BusinessName   : {}", );
        // 打印 Http method
        logInfo.append(String.format("HTTP Method    : %s %s", request.getMethod(), System.lineSeparator()));
        // 打印调用 controller 的全路径以及执行方法
        logInfo.append(String.format("Class Method   : %s：：%s %s", joinPoint.getTarget().getClass().getName() ,((MethodSignature) joinPoint.getSignature()).getName() , System.lineSeparator()));
        // 打印请求的 IP
        logInfo.append(String.format("IP             : %s %s", request.getRemoteHost(), System.lineSeparator()));
        // 打印请求入参
        logInfo.append(String.format("Request Args   : %s %s", joinPoint.getArgs(), System.lineSeparator()));
        log.info(logInfo.toString());
    }

    private void handleAfter(ProceedingJoinPoint joinPoint, Object obj) {
        StringBuilder logInfo = new StringBuilder();
        logInfo.append(String.format("%s=======Return======= %s", System.lineSeparator(), System.lineSeparator()));
        logInfo.append(String.format("Class Method   : %s：：%s %s", joinPoint.getTarget().getClass().getName() ,((MethodSignature) joinPoint.getSignature()).getName() , System.lineSeparator()));
        logInfo.append(String.format("Response       : %s %s", JSON.toJSONString(obj), System.lineSeparator()));
        log.info(logInfo.toString());
    }

    // private <T> void getAnnotation(ProceedingJoinPoint joinPoint, Class<T> logClazz) throws NoSuchMethodException {
    //     Class<?> targetCls = joinPoint.getTarget().getClass();
    //     MethodSignature ms = (MethodSignature) joinPoint.getSignature();
    //     //获取目标方法上的注解指定的操作名称
    //     Method targetMethod = targetCls.getDeclaredMethod(ms.getName(), ms.getParameterTypes());
    //     Annotation requiredLog = targetMethod.getAnnotation(logClazz);
    //     // String operation = requiredLog.value();
    //     // System.out.println("targetMethod="+targetMethod);
    //     return null;
    // }


}
