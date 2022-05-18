package com.rank.aop;

import com.rank.annotation.AspectAnnotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Caolp
 */
@Aspect
@Component
public class AspectDefinition {

    /**
     * 指定包含 {@link AspectAnnotation} 注解的才会被增强处理
     */
    @Pointcut("@annotation(com.rank.annotation.AspectAnnotation)")
    public void pointcutAnnotation(){

    }

    /**
     * 前置通知，执行目标方法之前进行通知
     * @param joinPoint 切点
     */
    @Before(value = "pointcutAnnotation()")
    public void doBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        System.out.println("前置通知: 方法名" + method + ", 参数: " + Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * 后置通知，执行目标方法之后进行通知
     * @param joinPoint 切点
     */
    @After(value = "pointcutAnnotation()")
    public void doAfter(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        System.out.println("后置通知: 方法名" + method + ", 参数: " + Arrays.asList(joinPoint.getArgs()));
    }

    /**
     * 返回通知，执行目标方法并返回参数后触发
     * @param joinPoint 切点
     * @param result 返回结果
     */
    @AfterReturning(value = "pointcutAnnotation()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result){
        String method = joinPoint.getSignature().getName();
        System.out.println("返回通知: 方法名" + method + ", 参数: " + Arrays.asList(joinPoint.getArgs()) + ", 结果: " + result);
    }

    /**
     * 异常通知，执行目标方法并出现异常时触发
     * @param joinPoint 切点
     * @param ex 异常
     */
    @AfterThrowing(value = "pointcutAnnotation()", throwing = "ex")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex){
        String method = joinPoint.getSignature().getName();
        System.out.println("异常通知: 方法名" + method + ", 参数: " + Arrays.asList(joinPoint.getArgs()) + ", 异常: " + ex.getMessage());
    }


}
