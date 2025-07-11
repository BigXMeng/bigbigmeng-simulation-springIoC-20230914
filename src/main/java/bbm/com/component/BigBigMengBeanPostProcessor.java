package bbm.com.component;

import bbm.com.annotation.*;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
@author Liu Xianmeng
@createTime 2023/9/18 22:00
@instruction BigBigMengBeanPostProcessor 具有实际功能的后置处理器
*/

@SuppressWarnings({"all"})
@Component // 后置处理器也是一个Bean 交给IoC容器进行管理
public class BigBigMengBeanPostProcessor implements BeanPostProcessor {

    /**
     * 注入切面对象 以调用其切面方法
     */
    @Autowired
    private BigBigMengAspect bigBigMengAspect;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        // 添加一句日志 表示此方法被执行 打印出当前处理的bean
        System.out.println("C BigBigMengBeanPostProcessor M postProcessBeforeInitialization -> bean = " + bean);
        return bean; // 处理后返回这个bean
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        // 添加一句日志 表示此方法被执行 打印出当前处理的bean
        System.out.println("C BigBigMengBeanPostProcessor M postProcessAfterInitialization -> bean = " + bean);
        // 反射获取bean的所有方法并遍历 如果发现方法有@TargetMethodLabel修饰
        // 则创建代理对象执行目标方法 在目标方法执行的前后切入切面类的方法
        Method[] declaredMethods = bean.getClass().getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            /**
             * 如果这个方法被 @TargetMethodLabel注解修饰
             * 则使用CGLib生成当前bean的代理对象并返回
             */
            if(declaredMethod.isAnnotationPresent(TargetMethodLabel.class)) {
                // 创建代理对象执行目标方法 在目标方法执行的前后切入切面类的方法
                // 创建一个Enhancer增强器实例
                Enhancer enhancer = new Enhancer();
                // 设置增强器要增强的父类Class对象 这个地方其实就是DuckDao的Class对象
                enhancer.setSuperclass(bean.getClass());
                /**
                 * 创建MethodInterceptor方法拦截器对象
                 * @param o 使用字节码生成的代理对象 personProxy
                 * @param method 父类中原本要执行的方法
                 * @param objects 方法在调用时传入的实参数组
                 * @param methodProxy 子类中重写父类的方法 personProxy
                 * @return  在目标对象上执行了指定的方法 并返回了该方法的结果
                 * @throws Throwable
                 */
                MethodInterceptor interceptor = new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        Object resultValue = null; // 用于接收method方法执行的返回值

                        /*** 如果被代理的DuckDao对象要执行的方法名 === declaredMethod.getName() 则对原目标方法进行增强 ***/
                        if(method.getName().equals(declaredMethod.getName())) {
                            /**
                             * 判断并执行BigBigMengAspect的@Before修饰的方法 对原目标方法进行增强
                             *
                             * 因为我只写了一个Aspect类 所以将其注入到BigBigMengBeanPostProcessor后
                             * 只需要判断这一个bigBigMengAspect切面类对象 如果有更多的切面类对象 则应该放在集合中进行管理 遍历处理
                             */
                            if(BigBigMengAspect.class
                                .getDeclaredMethod("beforeAdvice", Object.class)
                                .getDeclaredAnnotation(Before.class).value()
                                .equals("@annotation(targetMethodLabel)")){

                                bigBigMengAspect.beforeAdvice(bean);
                            }

                            // 🎯执行目标方法 由代理对象执行
                            resultValue = methodProxy.invokeSuper(o, objects);

                            // 执行BigBigMengAspect的@After修饰的方法
                            if(BigBigMengAspect.class
                                .getDeclaredMethod("afterAdvice", Object.class)
                                .getDeclaredAnnotation(After.class).value()
                                .equals("@annotation(targetMethodLabel)")){

                                bigBigMengAspect.afterAdvice(bean);
                            }
                        }
                        // 返回method方法执行的返回值
                        return resultValue;
                    }
                };
                enhancer.setCallback(interceptor);
                // 返回代理对象
                Object proxy = enhancer.create();
                System.out.println("C BigBigMengBeanPostProcessor M postProcessAfterInitialization() proxy = " + proxy.getClass());
                return proxy;
            }
        }
        // 没有被 @TargetMethodLabel 注解修饰的类不需要生成代理对象
        return bean;
    }
}
