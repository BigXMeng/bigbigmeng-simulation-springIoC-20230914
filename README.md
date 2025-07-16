# 概述
- dd-simu (DomainDominator-Simulation) 模拟、仿真实现
- 目的：深入理解框架原理及实践
```text
该工程为Java后端技术栈的编码模拟实现 当前包括： 
(1) Spring核心原理（IoC、AOP）模拟编码实现
(2) Rpc框架核心功能模拟编码实现
...
```
# 工程结构说明
```text
[dd-simu]
    - [dd-simu-IoC-AOP] -> Spring核心原理（IoC、AOP）模拟编码实现
    - [dd-simu-rpc] -> Rpc框架核心功能模拟编码实现和测试使用
        - [dd-simu-rpc-impl] -> Rpc框架核心功能模拟编码实现
        - [dd-simu-rpc-starter] -> 模拟starter依赖 以引入使用
        - [dd-simu-rpc-use] -> 测试使用
            - [dd-simu-rpc-use-provider] -> 测试使用 服务提供
            - [dd-simu-rpc-use-consumer] -> 测试使用 服务消费
```