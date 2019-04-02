消息发送到spring.app.name参数值的主题中，destination标识作为tag参数。

项目引用后，其它所需事项：

1.配置rocket参数：
rocketmq.namesrvAddr：xxx


2.需配置定时任务，执行com.zhulong.framework.localmessage.jpa.job.SendMsgJob中的excute方法。

3.需要spring能扫描档com.zhulong.framework.localmessage路径下的配置，如启动类与当前配置不同，则需要在spring-boot启动类上设置扫描包路径，如：
@SpringBootApplication(scanBasePackages={"com.zhulong.platform"})
