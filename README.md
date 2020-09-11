> # RocketMQ 使用
>
> ### MQ的作用
>
> - 应用解耦
> - 流量削锋
> - 数据分发
>
> 
>
> ### 使用 MQ 的缺点
>
> 可用性降低、MQ 宕机影响业务、系统复杂度提高（重复消费、消息丢失、消息顺序性）、一致性问题
>
>
> ### MQ使用
>
> #### 启动 NameSrv
>
> rocketmq 的默认端口 9876
>
> ```bash
> ./mqnamesrv &
> ```
>
>
> #### 启动 boker
>
> broker 的默认端口是 10911
>
> ```bash
> ./mqbroker -n localhost:9876  -c ../conf/broker.conf  autoCreateTopicEnable=true &
> ```
>
> #### 关闭 NameSrv
>
> ```bash
> ./mqshutdown namesrv
> ```
>
> #### 关闭 boker
>
> ```bash
> ./mqshutdown broker
> ```
>
> #### 测试 RocketMQ
>
> > # 官网文档Send & Receive Messages
> >
> > Before sending/receiving messages, we need to tell clients the location of name servers. RocketMQ provides multiple ways to achieve this. For simplicity, we use environment variable `NAMESRV_ADDR`
>
> ```
> > export NAMESRV_ADDR=localhost:9876
>  > sh bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
>  SendResult [sendStatus=SEND_OK, msgId= ...
>  > sh bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer
>  ConsumeMessageThread_%d Receive New Messages: [MessageExt...
> ```
>
>
> - 配置环境变量
>
> ```bash
> export NAMESRV_ADDR=localhost:9876
> ```
>
> - 生产消息
>
> ```bash
> ./tools.sh org.apache.rocketmq.example.quickstart.Producer
> ```
>
> - 消费消息
>
> ```bash
> ./tools.sh org.apache.rocketmq.example.quickstart.Consumer
> ```
>
>
> ### 消息发送
>
> **消息结构体:**
>
> - 消息主题 Topic
> - 消息标签 Tap
> - 消息内容 Content
>
> 
>
> #### 同步消息
>
> #### 异步消息
>
>
> ### 顺序发送
>
> 顺序消费的原理解析，在默认的情况下消息发送会采取Round Robin轮询方式把消息发送到不同的queue(分区队列)；而消费消息的时候从多个queue上拉取消息，这种情况发送和消费是不能保证顺序。但是如果控制发送的顺序消息只依次发送到同一个queue中，消费的时候只从这个queue上依次拉取，则就保证了顺序。当发送和消费参与的queue只有一个，则是全局有序；如果多个queue参与，则为分区有序，即相对每个queue，消息都是有序的。
>
> #### 生产
>
> 顺序发布中的实现，通过MessageQueueSelector selector设置一批相同的消息发送到相同的一个 Queue。
>
> #### 消费
>
> 
>
>
> 
>
> ### 参考文档
>
> [RocketMQ官网](https://rocketmq.apache.org/)
> [RocketMQ中文官方文档](https://github.com/apache/rocketmq/tree/master/docs/cn)
> [RocketMQ部署时遇到的问题](https://www.jianshu.com/p/bfd6d849f156)
> [RocketMQ.2-NameServer是如何启动的](https://segmentfault.com/a/1190000022921110)
>
> 
