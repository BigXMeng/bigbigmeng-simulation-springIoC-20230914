package com.dd.simu.iocaop.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
@author Liu Xianmeng
@createTime 2023/9/14 23:55
@instruction 鸭子pojo类 pojo类不作为组件进行管理
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Duck {

    String name; // 鸭子的名字
    int age; // 鸭子的年龄

    @Override
    public String toString() {
        return "Duck{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
