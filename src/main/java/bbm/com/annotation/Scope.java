package bbm.com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
@author Liu Xianmeng
@createTime 2023/9/14 18:32
@instruction @Scope注解用于指定一个Bean在IoC中应该是单例还是多例

            singleton or prototype
*/

@Target(ElementType.TYPE) // 表示@Scope用于修饰类
// 指定保留策略 RUNTIME标识运行的时候可以通过反射检测到该注解
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
    // 通过value可以指定 singleton prototype
    String value() default "singleton"; // 默认指定单例
}
