package com.dd.simu.iocaop.component.business;

import com.dd.simu.iocaop.annotation.Autowired;
import com.dd.simu.iocaop.annotation.Component;
import com.dd.simu.iocaop.annotation.Scope;
import com.dd.simu.iocaop.pojo.entity.Duck;

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
