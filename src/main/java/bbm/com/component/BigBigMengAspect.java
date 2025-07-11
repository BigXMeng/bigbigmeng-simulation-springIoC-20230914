package bbm.com.component;

import bbm.com.annotation.*;

/**
@author Liu Xianmeng
@createTime 2023/9/19 11:05
@instruction BigBigMengAspect中不实现JoinPoint切入点 简化实现使用Object对象来代替
*/

@Aspect
@Component
public class BigBigMengAspect {
    // 被TargetMethodLabel注解修饰的方法会在执行前 先执行beforeAdvice方法
    @Before("@annotation(targetMethodLabel)")
    public static void beforeAdvice(Object o) { // static方法方便在后置处理器BigBigMengBeanPostProcessor类中进行调用
        System.out.println("C BigBigMengAspect M beforeAdvice()切入到" + o.getClass() + "执行方法前");
    }
    // 被TargetMethodLabel注解修饰的方法会在执行后 执行afterAdvice方法
    @After("@annotation(targetMethodLabel)")
    public static void afterAdvice(Object o) {
        System.out.println("C BigBigMengAspect M beforeAdvice()切入到" + o.getClass() + "执行方法后");
    }
}
