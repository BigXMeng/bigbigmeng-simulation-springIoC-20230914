package bbm.com.component;

import bbm.com.annotation.Autowired;
import bbm.com.annotation.Component;
import bbm.com.annotation.Scope;

/**
@author Liu Xianmeng
@createTime 2023/9/15 0:17
@instruction duckController将作为一个组件被放入IoC容器 名字为duckController
*/

@SuppressWarnings({"all"})
@Component(name = "duckController")
@Scope("singleton") // 指定为单例对象
public class DuckController {

    @Autowired
    private DuckService duckService;

    // 通过鸭子的名字获取鸭子对象并返回
    public Duck getOneByName(String duckName) {
        System.out.println("C DuckController M getOneByName()..");
        // 使用注入的duckService属性
        return duckService.getOneByName(duckName);
    }
}
