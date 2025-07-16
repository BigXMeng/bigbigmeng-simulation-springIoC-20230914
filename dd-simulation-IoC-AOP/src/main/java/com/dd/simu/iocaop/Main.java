package com.dd.simu.iocaop;

import com.dd.simu.iocaop.pojo.entity.Duck;
import com.dd.simu.iocaop.component.business.DuckController;
import com.dd.simu.iocaop.ioc.AnnotationApplicationContextImpl;
import com.dd.simu.iocaop.ioc.SysConfig;

/**
@author Liu Xianmeng
@createTime 2023/9/15 10:54
@instruction
*/

@SuppressWarnings({"all"})
public class Main {
    public static void main(String[] args) {
        // 获取IoC容器
        AnnotationApplicationContextImpl ioc = new AnnotationApplicationContextImpl(SysConfig.class);
        // 从IoC容器中获取DuckController对象
        DuckController duckController = (DuckController) ioc.getBean("duckController");
        // 调用duckControllerd的getOneByName方法
        Duck littleBlack = duckController.getOneByName("littleBlack");
        // 打印获取到的Duck对象
        System.out.println(littleBlack);
    }
}
