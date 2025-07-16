package com.dd.simu.iocaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
@author Liu Xianmeng
@createTime 2023/9/19 10:52
@instruction
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // @Aspect注解修饰切面类
public @interface Aspect {
    // 目前不需要属性
}
