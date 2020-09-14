# RocketMQ-Demo项目说明

## MQ 简介
### MQ的作用

- 应用解耦
- 流量削锋
- 数据分发



### 使用 MQ 的缺点
可用性降低、MQ 宕机影响业务、系统复杂度提高（重复消费、消息丢失、消息顺序性）、一致性问题


## RocketMQ 单机启动
### 启动 NameSrv
rocketmq 的默认端口 9876
```bash
./mqnamesrv &
```


### 启动 boker
broker 的默认端口是 10911
```bash
./mqbroker -n localhost:9876  -c ../conf/broker.conf  autoCreateTopicEnable=true &
```
### 关闭 NameSrv
```bash
./mqshutdown namesrv
```
### 关闭 boker
```bash
./mqshutdown broker
```
## 测试 RocketMQ是否正常运行
> # 官网文档Send & Receive Messages
> Before sending/receiving messages, we need to tell clients the location of name servers. RocketMQ provides multiple ways to achieve this. For simplicity, we use environment variable `NAMESRV_ADDR`

```
> export NAMESRV_ADDR=localhost:9876
 > sh bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
 SendResult [sendStatus=SEND_OK, msgId= ...
 > sh bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer
 ConsumeMessageThread_%d Receive New Messages: [MessageExt...
```


- 配置环境变量
```bash
export NAMESRV_ADDR=localhost:9876
```

- 生产消息
```bash
./tools.sh org.apache.rocketmq.example.quickstart.Producer
```

- 消费消息
```bash
./tools.sh org.apache.rocketmq.example.quickstart.Consumer
```
## RocketMQ 说明
### 消息生产者
[RocketMQ 消息生产者详细说明](https://www.yuque.com/sourlemon/java/gpomnq)


### 消息消费者
[RocketMQ 消息消费者详细说明](https://www.yuque.com/sourlemon/java/ztwokw)

### 消息事务
[RocketMQ 事务详解](https://www.yuque.com/sourlemon/java/xehd3c)


### 集成 Springboot
[RocketMQ 集成 Springboot](https://www.yuque.com/sourlemon/java/aayzph)




## 参考文档

- [RocketMQ官网](https://rocketmq.apache.org/)
- [RocketMQ中文官方文档](https://github.com/apache/rocketmq/tree/master/docs/cn) 
- [RocketMQ部署时遇到的问题](https://www.jianshu.com/p/bfd6d849f156)
- [RocketMQ.2-NameServer是如何启动的](https://segmentfault.com/a/1190000022921110)
- [RocketMQ中broker配置brokcerIP1和brokerIP2的作用](https://blog.csdn.net/jiajiren11/article/details/80528406)
- [RocketMQ多端口监听](https://blog.csdn.net/weixin_36303817/article/details/107513425)
- [阿里中间件官方博客-十分钟入门RocketMQ](http://jm.taobao.org/2017/01/12/rocketmq-quick-start-in-10-minutes/)
- [《深入理解RocketMQ》- MQ消息的投递机制](https://louluan.blog.csdn.net/article/details/91368332)
