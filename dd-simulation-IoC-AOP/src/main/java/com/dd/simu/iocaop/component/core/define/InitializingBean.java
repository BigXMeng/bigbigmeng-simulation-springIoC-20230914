package com.dd.simu.iocaop.component.core.define;

/**
@author Liu Xianmeng
@createTime 2023/9/19 10:11
@instruction
*/
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
