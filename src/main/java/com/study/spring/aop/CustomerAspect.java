package com.study.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

// aop执行循序, 环绕增强 -> 前置增强 -> 最终增强 -> 后置增强 -> 环绕增强 -> 引用增强
@Aspect
@Component
public class CustomerAspect {

    @Before(value = "@annotation(com.study.spring.aop.AroundTag)")
    public void before(JoinPoint joinPoint) {
        System.out.println("【前置增强】,执行的方法名是:" + joinPoint.getSignature().getName()
            + ",参数为:" + Arrays.toString(joinPoint.getArgs()));
    }

    @Around(value = "@annotation(com.study.spring.aop.AroundTag)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("【环绕增强前】,执行的方法名是:" + joinPoint.getSignature().getName());
        final Object result = joinPoint.proceed();
        System.out.println("【环绕增强后】,执行的返回值是:" + result);
        return result;
    }

    @After(value = "@annotation(com.study.spring.aop.AroundTag)")
    public void after(JoinPoint joinPoint) {
        System.out.println("【后置增强】,执行的方法名是:" + joinPoint.getSignature().getName()
                + ",参数为:" + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(value = "@annotation(com.study.spring.aop.AroundTag)", returning = "o")
    public void afterReturning(Object o) {
        System.out.println("【最终增强】,当前程序返回值是:" + o);
    }

    @AfterThrowing(value = "execution(public * com.study.spring.aop.*.*(..))", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        System.out.println("【异常增强】,当前程序抛出异常的方法是:" + joinPoint.getSignature()
            + ", 请求参数为:" + Arrays.toString(joinPoint.getArgs()) + ",异常信息为:" + e.getMessage());

    }

    // 引入增强，在不修改代码的情况下，将一个已经代理的类，引入新的方法
    // 这里将CustomerServiceImpl引入GoodsService的功能
    // "+"表示CustomerService的所有子类;defaultImpl表示默认需要添加的新的类
//    @DeclareParents(value = "com.study.spring.aop.CustomerServiceImpl", defaultImpl = GoodsServiceImpl.class)
    @DeclareParents(value = "com.study.spring.aop.CustomerService+", defaultImpl = GoodsServiceImpl.class)
    public GoodsService goodsService;
}
