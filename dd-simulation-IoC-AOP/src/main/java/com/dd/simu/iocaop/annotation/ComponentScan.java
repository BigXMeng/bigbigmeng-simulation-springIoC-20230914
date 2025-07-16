package com.dd.simu.iocaop.annotation;

import java.lang.annotation.*;

/**
@author Liu Xianmeng
@createTime 2023/9/14 22:44
@instruction 这是@ComponentScan注解 这个注解用来指定IoC要扫描的包
             从而在包下发现Beans并装到IoC容器中
*/

/*
@Retention(RetentionPolicy.RUNTIME)是Java中的一个注解元注解
它指定了被注解的元素在运行时可以通过反射被访问到
具体来说 @Retention(RetentionPolicy.RUNTIME)表示该注解在运行时保留
因此可以在运行时通过反射API获取到被注解元素的信息

Java中的注解有不同的保留策略 包括源代码（RetentionPolicy.SOURCE）
编译时（RetentionPolicy.CLASS）和运行时（RetentionPolicy.RUNTIME）
其中 @Retention注解用来指定注解的保留策略 默认为RetentionPolicy.CLASS 即在编译时保留注解信息
但在运行时无法通过反射获取 而设置为RetentionPolicy.RUNTIME之后 注解将在运行时保留
意味着我们可以在程序运行期间动态地获取和使用注解信息

通常情况下 如果我们需要在运行时使用反射来处理注解信息 比如自定义注解处理器
那么就需要将注解的保留策略设置为RetentionPolicy.RUNTIME 以便在运行时能够获取注解信息并进行相应的处理

[如果在定义注解时没有指定@Retention注解的保留策略呢？]
这时默认情况下将使用RetentionPolicy.CLASS作为保留策略 这意味着该注解在编译时会被保留
但在运行时无法通过反射获取到注解信息
 */
@Retention(RetentionPolicy.RUNTIME)

/*
该注解用于指示该注解可以应用于类 接口或枚举类型的声明
@Target(ElementType.TYPE)表示该注解只能应用于类 接口和枚举类型的声明上 不能应用于其他类型的声明上

还有其他的指定作用范围 如下
@Target(ElementType.FIELD) 应用于字段（成员变量）上
@Target(ElementType.METHOD) 应用于方法上
@Target(ElementType.PARAMETER) 应用于方法参数上
@Target(ElementType.CONSTRUCTOR) 应用于构造方法上
@Target(ElementType.LOCAL_VARIABLE) 应用于局部变量上
@Target(ElementType.ANNOTATION_TYPE) 应用于注解类型

指定注解作用范围的意义是什么？提供更明确的编程规范和约束
 */
@Target(ElementType.TYPE)
public @interface ComponentScan {
    String value() default ""; // 通过value指定要扫描的包
}

