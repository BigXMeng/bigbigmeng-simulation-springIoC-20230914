package bbm.com.component;

/**
@author Liu Xianmeng
@createTime 2023/9/15 0:13
@instruction DuckService将作为一个组件被放入IoC容器 名字为duckService
*/

import bbm.com.annotation.Autowired;
import bbm.com.annotation.Component;
import bbm.com.annotation.Scope;

@SuppressWarnings({"all"})
@Component(name = "duckService")
@Scope("singleton") // 指定为单例对象
public class DuckService {

    @Autowired
    private DuckDao duckDao; // 注入容器中此对象

    // 通过鸭子的名字获取鸭子对象并返回
    public Duck getOneByName(String duckName) {
        System.out.println("C DuckService M getOneByName()..");
        // 使用注入的duckDao属性
        return duckDao.getOneByName(duckName);
    }

}
