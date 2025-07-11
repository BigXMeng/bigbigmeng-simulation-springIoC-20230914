package bbm.com.component;

/**
@author Liu Xianmeng
@createTime 2023/9/19 10:11
@instruction
*/

@SuppressWarnings({"all"})
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
