package bbm.com.component.business;

import bbm.com.annotation.Component;
import bbm.com.annotation.Scope;
import bbm.com.annotation.TargetMethodLabel;
import bbm.com.component.core.define.InitializingBean;
import bbm.com.pojo.entity.Duck;

import java.util.concurrent.ConcurrentHashMap;

/**
@author Liu Xianmeng
@createTime 2023/9/15 0:03
@instruction DuckDao将作为一个组件被放入IoC容器 名字为duckDao
*/

@SuppressWarnings({"all"})
@Component(name = "duckDao")
@Scope("singleton") // 指定为单例对象
public class DuckDao implements InitializingBean {

    // 用一个ConcurrentHashMap模拟数据库的数据
    public static ConcurrentHashMap<String, Duck> data;

    // 构造器初始化数据
    public DuckDao() {
        data = new ConcurrentHashMap<>();
        data.put("littleYellow", new Duck("littleYellow", 2));
        data.put("diandian", new Duck("diandian", 1));
        data.put("littleBlack", new Duck("littleBlack", 3));
    }

    // 通过鸭子的名字获取鸭子对象并返回
    @TargetMethodLabel
    public Duck getOneByName(String duckName) {
        System.out.println("C DuckDao M getOneByName()目标方法执行..");
        return data.get(duckName);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("C DuckDao M afterPropertiesSet() -> initializing");
    }
}
