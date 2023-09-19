package bbm.com.component;

import bbm.com.annotation.Component;

/**
@author Liu Xianmeng
@createTime 2023/9/14 23:55
@instruction 鸭子pojo类 pojo类不作为组件进行管理
*/

@SuppressWarnings({"all"})
public class Duck {
    String name; // 鸭子的名字
    int age; // 鸭子的年龄

    public Duck() {
    }
    public Duck(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Duck{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
    }
}
