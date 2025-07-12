package bbm.com.component.business;

import bbm.com.annotation.Autowired;
import bbm.com.annotation.Component;
import bbm.com.annotation.Scope;
import bbm.com.pojo.entity.Duck;

/**
@author Liu Xianmeng
@createTime 2023/9/15 0:17
@instruction duckController将作为一个组件被放入IoC容器 名字为duckController
*/
@Scope("singleton") // 指定为单例对象
@Component(name = "duckController")
public class DuckController {

    @Autowired
    private DuckService duckService;

    // 通过鸭子的名字获取鸭子对象并返回
    //@TargetMethodLabel
    public Duck getOneByName(String duckName) {
        System.out.println("C DuckController M getOneByName()..");
        // 使用注入的duckService属性
        return duckService.getOneByName(duckName);
    }
}
