package com.zhulong.framework.autoconfig;

/*import com.zhulong.framework.tcc.AddTccContextInterceptor;
import com.zhulong.framework.tcc.SetTccContextFilter;
import com.zhulong.framework.tcc.ZhulongTransactionContextEditor;
import feign.RequestInterceptor;
import lombok.Getter;
import lombok.Setter;
import org.mengyun.tcctransaction.TransactionRepository;
import org.mengyun.tcctransaction.repository.RedisTransactionRepository;
import org.mengyun.tcctransaction.serializer.KryoThreadLocalSerializer;
import org.mengyun.tcctransaction.serializer.ObjectSerializer;
import org.mengyun.tcctransaction.spring.recover.DefaultRecoverConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

*//**
 * TCC事务自动装配功能
 * Created by shanb on 2019-3-1.
 *//*

@Configuration
@ConditionalOnClass(ZhulongTransactionContextEditor.class)
@ConditionalOnResource(resources = {"classpath:tcc-transaction.xml"})
@ImportResource("classpath:tcc-transaction.xml")
@AutoConfigureAfter(DataSourceAutoConfiguration.class)*/
public class TccTransactionAutoConfig {

/*    @Value("${spring.application.name}")
    private String appName;

    @Bean
    @ConditionalOnClass(RequestInterceptor.class)
    public AddTccContextInterceptor addTccContextInterceptor(){
        return new AddTccContextInterceptor();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        SetTccContextFilter setTccContextFilter = new SetTccContextFilter();
        registrationBean.setFilter(setTccContextFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("*//*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }

    @Bean
    @ConfigurationProperties("zhulong.tcc.jedis")
    @ConditionalOnMissingBean(TccRedisConfigProperties.class)
    public TccRedisConfigProperties tccRedisConfigProperties(){
        return new TccRedisConfigProperties();
    }

    @Bean
    @ConditionalOnMissingBean(TransactionRepository.class)
    public TransactionRepository transactionRepository(TccRedisConfigProperties tccRedisConfigProperties) {
        //暂时只支持到单机版的redis，
        JedisPool jedisPool = new JedisPool(tccRedisConfigProperties.getPoolConfig(),tccRedisConfigProperties.getHost(),tccRedisConfigProperties.getPort(),tccRedisConfigProperties.timeout,tccRedisConfigProperties.getPassword());
        RedisTransactionRepository redisTransactionRepository = new RedisTransactionRepository();
        redisTransactionRepository.setJedisPool(jedisPool);
        redisTransactionRepository.setKeyPrefix("tcc:"+appName);
        redisTransactionRepository.setSerializer(objectSerializer());
        return redisTransactionRepository;
    }

    @Bean
    @ConditionalOnMissingBean(DefaultRecoverConfig.class)
    public DefaultRecoverConfig recoverConfig(){
        DefaultRecoverConfig recoverConfig = new DefaultRecoverConfig();
        recoverConfig.setMaxRetryCount(10);
        recoverConfig.setCronExpression("0 *//*1 * * * ?");
        recoverConfig.setRecoverDuration(120);
        //配置超时异常 如果发生了此类异常  不立即进行回滚  交给定时任务去进行回归
//        recoverConfig.setDelayCancelExceptions();
        return recoverConfig;
    }

    @Bean
    @ConditionalOnMissingBean(ObjectSerializer.class)
    public ObjectSerializer<?> objectSerializer() {
        return new KryoThreadLocalSerializer();
    }

    @Getter
    @Setter
    public static class TccRedisConfigProperties{

        private JedisPoolConfig poolConfig;

        private String host;

        private int port = 6379;

        private int timeout = 2000;

        private String password;
    }*/
}