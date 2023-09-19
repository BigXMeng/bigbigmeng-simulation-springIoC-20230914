package bbm.com.component;

import bbm.com.annotation.Component;

/**
@author Liu Xianmeng
@createTime 2023/9/18 22:00
@instruction BigBigMengBeanPostProcessor 具有实际功能的后置处理器
*/

@SuppressWarnings({"all"})
@Component // 后置处理器也是一个Bean 交给IoC容器进行管理
public class BigBigMengBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        // 添加一句日志 表示此方法被执行 打印出当前处理的bean
        System.out.println("C BigBigMengBeanPostProcessor M 初始化前 -> bean = " + bean);
        return bean; // 处理后返回这个bean
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        // 添加一句日志 表示此方法被执行 打印出当前处理的bean
        System.out.println("C BigBigMengBeanPostProcessor M 初始化后 -> bean = " + bean);
        return bean; // 处理后返回这个bean
    }
}
