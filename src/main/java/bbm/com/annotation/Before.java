package bbm.com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
@author Liu Xianmeng
@createTime 2023/9/19 10:55
@instruction
*/

@SuppressWarnings({"all"})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // @Before注解作用于方法
public @interface Before {
    /**
     * 指定注解的名字
     *
     * 例如 @Before(value="@annotation(用于标识切面方法要作用的普通方法的注解 的变量名)")
     * 具体可见后面编写的BigBigMengAspect切面类
     */
    String value() default "";
}
