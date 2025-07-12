package bbm.com.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
@author Liu Xianmeng
@createTime 2023/9/19 11:02
@instruction
*/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface After {
    // 同 @Before
    String value() default "";
}
