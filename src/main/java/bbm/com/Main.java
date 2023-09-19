package bbm.com;

import bbm.com.component.Duck;
import bbm.com.component.DuckController;
import bbm.com.ioc.BigBigMengAnnotationApplicationContext;
import bbm.com.ioc.BigBigMengConfig;

/**
@author Liu Xianmeng
@createTime 2023/9/15 10:54
@instruction
*/

@SuppressWarnings({"all"})
public class Main {
    public static void main(String[] args) {
        // 获取IoC容器
        BigBigMengAnnotationApplicationContext ioc = new BigBigMengAnnotationApplicationContext(BigBigMengConfig.class);
        // 从IoC容器中获取DuckController对象
        DuckController duckController = (DuckController) ioc.getBean("duckController");
        // 调用duckControllerd的getOneByName方法
        Duck littleBlack = duckController.getOneByName("littleBlack");
        // 打印获取到的Duck对象
        System.out.println(littleBlack);
    }
}
