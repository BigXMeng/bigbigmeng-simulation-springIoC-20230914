package bbm.com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
@author Liu Xianmeng
@createTime 2023/9/20 0:46
@instruction 用于标识切入方法要切入的目标方法
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) // 用于修饰方法
public @interface TargetMethodLabel {
    // 只用于标识 不需要参数
}
