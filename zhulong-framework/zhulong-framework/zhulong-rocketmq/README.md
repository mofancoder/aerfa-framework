用于rocketmq简单集成spring，并支持注解标记的方法作为消费逻辑。

具体方式如下展示所示：

生产者：
@Configuration
public class RocketMQProduceConfig {

    @Bean
    public DefaultMQProducer localMessageProduce(@Value("${rocketmq.namesrvAddr}") String namesrvAddr, @Value("${spring.application.name}") String groupName,@Value("${rocketmq.localmessage.instanceName:''}") String instanceName){
        ProduceConfigBean configBean = new ProduceConfigBean();
        configBean.setNamesrvAddr(namesrvAddr);
        configBean.setGroupName(groupName);
        configBean.setInstanceName(instanceName);
        DefaultMQProducer defaultMQProducer = configBean.getProducer();
        return defaultMQProducer;
    }
}

消费者：
@Configuration
public class RocketMQConsumeConf {

    //启动@RocketMQstreamLister注解
    @Bean
    public BeanPostProcessor rocketMQPostProcess(){
        return new RocketMQAnnotionPostProcess();
    }

    @Bean(initMethod = "init")
    public ConsumerConfigBean demoTestConsumer(@Value("${rocketmq.namesrvAddr}")String namesrvAddr,@Value("${spring.application.name}")String groupName
        ,@Value("${rocketmq.demotopic.tag:'*'}")String tag){
        ConsumerConfigBean consumerConfigBean = new ConsumerConfigBean();
        consumerConfigBean.setNamesrvAddr(namesrvAddr);
        consumerConfigBean.setGroupName(groupName+"1");
        Map<String,String> topicTagMap = new HashMap<>();
        topicTagMap.put("demo",tag);
        consumerConfigBean.setTopicTag(topicTagMap);
        return consumerConfigBean;
    }

}

@Component
public class MqConsumer {

    @RocketMQStreamLister(topic = "demo",tag="demo-test")
    public void consumeMessage(Message message){
        try {
            System.out.println(new String(message.getBody(),"UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}