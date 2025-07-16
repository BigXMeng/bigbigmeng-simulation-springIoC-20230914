package com.dd.simu.iocaop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
@author Liu Xianmeng
@createTime 2023/9/14 23:37
@instruction @Autowired修饰Bean中的变量域 如果类中的某变量被@Autowired修饰
             这个变量对应的类对象就应该被注入到这个类中
             所谓的注入 其实就是将singletonObjects中此变量的类对象的引用赋值给这个变量
*/
@SuppressWarnings({"all"})
@Target(ElementType.FIELD) // 用于修饰变量域
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    String name() default ""; // 指定注入的变量的名字
}
