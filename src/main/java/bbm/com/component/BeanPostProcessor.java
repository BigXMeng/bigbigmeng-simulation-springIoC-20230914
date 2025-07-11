package bbm.com.component;

import com.sun.istack.internal.Nullable;

/**
@author Liu Xianmeng
@createTime 2023/9/18 21:57
@instruction
*/

@SuppressWarnings({"all"})
public interface BeanPostProcessor {
    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Nullable
    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}

