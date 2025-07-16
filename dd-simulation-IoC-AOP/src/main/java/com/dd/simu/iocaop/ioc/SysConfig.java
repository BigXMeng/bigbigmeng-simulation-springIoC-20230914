package com.dd.simu.iocaop.ioc;

import com.dd.simu.iocaop.annotation.ComponentScan;

/**
@author Liu Xianmeng
@createTime 2023/9/14 22:43
@instruction BigBigMengConfig配置类的@ComponentScan注解指定了
             IoC容器应该要扫描的包 该包下的所有被注解标识的Beans
             都会被IoC容器进行创建和管理
*/
@ComponentScan(value = "com.dd.simu.iocaop.component")
public class SysConfig {
}
