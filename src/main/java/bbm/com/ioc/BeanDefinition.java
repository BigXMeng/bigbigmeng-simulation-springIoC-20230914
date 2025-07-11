package bbm.com.ioc;

/**
@author Liu Xianmeng
@createTime 2023/9/14 18:24
@instruction 这是BeanDefinition类
*/

@SuppressWarnings({"all"})
public class BeanDefinition {

    /****** 目前 仅需要这两个属性 如果模拟实现的功能更加复杂 可以进行拓展 ******/

    /**
     * 指定Bean是单例还是多例 singleton | prototype
     *
     * 当IoC扫描包并遇到一个Class对象的时候 它会检查这个类是否有@Scope注解修饰
     * 如果没有@Scope注解修饰则默认将这个Bean的定义信息的scope值为singleton
     * 如果有@Scope注解修饰 则看@Scope指定的值
     *
     * @Scope注解的定义如下：
     *
     * public @interface Scope {
     *     // 通过value可以指定 singleton prototype
     *     String value() default "singleton"; // 默认指定单例
     * }
     */
    private String scope;

    /**
     * IoC容器初始化的时候会第一遍扫描容器
     *
     * 扫描到Class对象的时候 直接将其引用赋给该属性
     */
    private Class clazz;  // Bean的Class对象

    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
    public Class getClazz() {
        return this.clazz;
    }
    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }
    @Override
    public String toString() {
        return "BeanDefinition{" +
            "scope='" + scope + '\'' +
            ", clazz=" + clazz +
            '}';
    }
}
