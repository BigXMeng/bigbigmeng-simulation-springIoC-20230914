package bbm.com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
@author Liu Xianmeng
@createTime 2023/9/14 23:33
@instruction @Component注解用于标识一个Java类是一个Bean 标识后 它将被IoC容器管理
*/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {
    String name() default ""; // 指定bean的名字
}
