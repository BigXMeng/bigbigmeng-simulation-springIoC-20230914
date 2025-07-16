package com.dd.simu.iocaop.pojo.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author liuxianmeng
 * @CreateTime 2025/7/12 9:06
 * @Description BeanScopeEnum 单例 or 多例
 */
@Getter
@AllArgsConstructor
public enum BeanScopeEnum {
    SINGLETON("singleton", "单例"),
    PROTOTYPE("prototype", "多例"),;

    private final String code;
    private final String desc;
}
