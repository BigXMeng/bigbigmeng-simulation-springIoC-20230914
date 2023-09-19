package bbm.com.ioc;

import bbm.com.annotation.Autowired;
import bbm.com.annotation.Component;
import bbm.com.annotation.ComponentScan;
import bbm.com.annotation.Scope;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
@author Liu Xianmeng
@createTime 2023/9/14 17:27
@instruction IoC容器类 这个类就是要模拟实现额IoC容器类
            下面将BigBigMengAnnotationApplicationContext的类名简称为 "IoC容器"

            【说明】在注释中如果出现前文未出现的名词 (如对于 private Class configClass; 的注释中出现singletonObjects)
                   可以在后文中找到
*/

@SuppressWarnings({"all"})
public class BigBigMengAnnotationApplicationContext { // IoC容器

    /**
     * IoC容器持有一个配置类的Class对象
     *
     * 这个类的定义如下:
     * @ComponentScan(value = "IoC容器要扫描的包名")
     * public class BigBigMengSpringConfig {}
     *
     * [IoC容器注入Bean]
     * 这个类的@ComponentScan注解的value指定IoC容器要扫描的包 IoC容器会使用反射获取这个类的@ComponentScan注解
     * 然后将 value = "IoC容器要扫描的包名" -> 包名取出 然后扫描包中所有的Class对象
     * 如果扫描到到的Class对象有类似@Component、@Bean、@Mapper、@Service、@Controller等标识这个Class对象是一个
     * 组件的注解修饰 就创建一个此Class对象对应的Java类对象 并放入singletonObjects
     *
     * [IoC容器注入Bean的依赖Bean]
     * 在扫描到一个Class对象后 不仅要创建该Class对象对应的Java类对象 还需要扫描其所有的属性 如果其某个属性A有
     *  @Autowire @Resource等标识 则该属性A应该被注入到该当前创建的Java对象 如果singletonObjects已经存在该属性A对象
     * 则直接从singletonObjects中取出相应的Java对象并将引用赋值给该属性A 如果singletonObjects还不存在该属性A对象
     * 则创建对应的A属性Java对象放入singletonObjects 并将此Java对象的引用赋值给属性A 这样就完成了属性的依赖注入
     *
     * 直到配置类指定的包中的所有的Class对象都被创建并放入singletonObjects 整个包扫描的过程就完成了
     */
    private Class configClass;

    /**
     * 定义属性BeanDefinitionMap -> 存放BeanDefinition对象
     *
     * IoC容器中有一个变量域/也称为属性 叫beanDefinitionMap 这个map用来保存Bean的定义信息
     * 为什么要存储Bean的定义信息？因为在创建Bean的时候要用到
     * 也就是说IoC容器会先扫描1遍配置类指定的包获取所有Bean的定义信息
     * 然后遍历beanDefinitionMap创建所有的Bean放入singletonObjects
     *
     * beanDefinitionMap是ConcurrentHashMap的一个对象 键是Bean的名字 值是Bean的定义信息
     * Bean的定义信息由BeanDefinition类的对象来充当
     */
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 定义属性SingletonObjects -> 存放单例对象
     *
     * singletonObjects就是我上面提到的存放单例对象的变量 在上文已经出现过
     * 它也是一个ConcurrentHashMap对象 键是Bean的名字 值是Bean对象本身
     */
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 接下来是IoC容器的构造器
     *
     * 构造器中完成两个任务
     * （1）初始化Beans的定义信息beanDefinitionMap
     * （2）遍历Beans的定义信息beanDefinitionMap初始化单例池singletonObjects
     *
     * @param configClass 在创建一个IoC容器对象的时候 需要传入配置类的Class对象
     *                    配置类的Class对象 上文已经提到 IoC容器
     */
    public BigBigMengAnnotationApplicationContext(Class configClass) {
        this.configClass = configClass;
        beanDefinitionsByScan(this.configClass);
        initialSingletonObjects();
    }

    /**
     * beanDefinitionsByScan方法完成对指定包的扫描 并将Bean信息封装到beanDefinitionMap
     */
    public void beanDefinitionsByScan(Class configClass) {

        /*********** 获取要扫描的包 ***********/

        // 首先先获取配置类的注解
        ComponentScan scanAnnotation =
            (ComponentScan) this.configClass.getDeclaredAnnotation(ComponentScan.class);
        // 然后通过componentScan的value值获取要扫描的包
        // value()是ComponentScan注解的一个属性 带括号显得有些别扭 但这是注解的规范
        String packageName = scanAnnotation.value();
        System.out.println("C IoC M beanDefinitionsByScan() -> IoC要扫描的包 = " + packageName);

        /*********** 根据类加载器获取要扫描的资源 ***********/

        // 获取类加载器
        ClassLoader classLoader = BigBigMengAnnotationApplicationContext.class.getClassLoader();
        // 通过类的加载器获取到要扫描的包的资源
        String path = packageName.replace(".", "/");
        URL resource = classLoader.getResource(path);
        System.out.println("C IoC M beanDefinitionsByScan() -> 包的完整路径 =" + resource);

        /*********** 遍历指定包下的所有文件 找出.class结尾的文件 将其信息封装到beanDefinitionMap ***********/

        // 获取指定包所在的包文件夹
        File file = new File(resource.getFile());
        // 遍历文件夹下所有的文件
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for(File subFile : files) {
                // 获取此subFile的全路径
                String absolutePath = subFile.getAbsolutePath();
                // 只处理.class文件
                if(absolutePath.endsWith(".class")) {
                    // 获取类名 注意这里的类并不一定需要注入 还需要判断它是否被@Component、@Service、@Mapper等注解修饰
                    String className = absolutePath.substring(absolutePath.lastIndexOf("\\") + 1, absolutePath.indexOf(".class"));
                    // 获取全类名
                    String classFullName = packageName + "." + className;

                    try{
                        // 获取classFullName对应的class对象
                        Class<?> clazz = classLoader.loadClass(classFullName);
                        // 判断该class对象是否被@Component、@Service、@Mapper等注解修饰
                        // 我在模拟实现的过程中只定义了一个标识Bean的注解 即@Component
                        if(clazz.isAnnotationPresent(Component.class)) {
                            System.out.println("C IoC M beanDefinitionsByScan() -> " + classFullName + "是一个Spring bean, 将其注入IoC容器进行管理");

                            /*********** 将其信息封装到beanDefinitionMap ***********/

                            // 获取该class对象的@Component
                            Component componentAnnotation = clazz.getDeclaredAnnotation(Component.class);
                            // 获取该bean的名字
                            String beanName = componentAnnotation.name();
                            if ("".equals(beanName)) { // 如果没有配置名字
                                // 则将该类的类名首字母小写作为beanName
                                beanName = StringUtils.uncapitalize(className);
                            }
                            // 将Bean的信息封装到BeanDefinition对象
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(clazz);
                            // 获取@Scope并设置beanDefinition对象的scope信息
                            if(clazz.isAnnotationPresent(Scope.class)) {
                                Scope scope = clazz.getDeclaredAnnotation(Scope.class);
                                beanDefinition.setScope(scope.value());
                            } else {
                                beanDefinition.setScope("singleton");
                            }
                            // 将beanDefinition放入beanDefinitionMap
                            beanDefinitionMap.put(beanName, beanDefinition);
                        } else {
                            System.out.println("C IoC M beanDefinitionsByScan() -> " + classFullName + "不是一个Spring bean 不需要注入IoC容器进行管理");
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * initialSingletonObjects方法beanDefinitionMap单例池的初始化
     */
    public void initialSingletonObjects(){
        // 获取所有beans的定义信息keys
        Enumeration<String> keys = beanDefinitionMap.keys();
        while (keys.hasMoreElements()) {
            // 得到beanName
            String beanName = keys.nextElement();
            // 通过beanName 得到对应的beanDefinition对象
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            // 判断该bean是singleton还是prototype 只初始化创建单例对象放入singletonObjects单例池
            if ("singleton".equalsIgnoreCase(beanDefinition.getScope())) {
                //将该bean实例放入到singletonObjects 集合
                Object bean = createBean(beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }

    /**
     * createBean方法完成Bean对象的创建
     *
     * @param beanName          传入Bean的名字(目前用不到这个看参数 这个参数会在实现AOP的时候用到)
     * @param beanDefinition    传入Bean的定义信息
     * @return                  返回创建的Bean对象
     */
    private Object createBean(BeanDefinition beanDefinition) {
        // 获取要创建的bean的Class对象 使用反射创建该对象
        Class clazz = beanDefinition.getClazz();
        try {
            // 获取构造方法并创建对象
            Constructor constructor = clazz.getDeclaredConstructor();
            Object bean = constructor.newInstance();

            // 接下来创建其属性
            for(Field field : clazz.getDeclaredFields()) {
                // 只有这个属性被@Autowired注解修饰 才需要被注入
                if(field.isAnnotationPresent(Autowired.class)){
                    /* 按照名字进行组装 */
                    String name = field.getName();

                    // 通过getBean方法来获取要组装对象
                    Object fieldBean = getBean(name);

                    // 进行组装
                    field.setAccessible(true); // 因为属性都是修饰的所以需要设置可访问
                    field.set(bean, fieldBean);
                }
            }

            // 所有的属性注入完成后返回当前的Bean对象
            return bean;

        }catch (Exception e) {
            System.out.println("不存在该构造方法");
        }

        // 创建失败则返回null
        return null;
    }

    /**
     * getBean 根据传入的Bean的name返回singletonObjects中的bean对象
     *
     * 这个方法会在给Bean注入属性对象的时候用到 注入属性对象的时候
     * 先检查singletonObjects中是否已经存在要注入的Bean 如果已经存在则直接取出用
     * 如果还未创建 则创建一个属性对象的Bean放入singletonObjects并将其引用赋值给要注入的属性
     *
     * @param   name
     * @return  返回singletonObjects中的bean对象
     */
    public Object getBean(String name) {
        // 先判断该name是否存在于beanDefinitionMap的KEYS当中
        if(beanDefinitionMap.containsKey(name)) {
            // 存在则获取该bean的定义信息
            BeanDefinition beanDefinition = beanDefinitionMap.get(name);
            // 然后判断是该bean的Scope是单例还是多例
            if("singleton".equals(beanDefinition.getScope())) {
                // 如果是单例 则直接从单例池获取
                Object o = singletonObjects.get(name);
                // 如果获取不到 则先创建
                if(o == null) {
                    o = createBean(beanDefinition);
                    // 创建后放入单例池并返回
                    singletonObjects.put(name, o);
                    return o;
                }
                // 不为空则直接返回
                return o;
            } else {
                // 如果是多例 则每次都使用createBean返回一个新的Bean返回
                return createBean(beanDefinition);
            }
        } else {
            // 不存在则抛出空指针异常
            throw new NullPointerException("该Bean不存在");
        }
    }
}
