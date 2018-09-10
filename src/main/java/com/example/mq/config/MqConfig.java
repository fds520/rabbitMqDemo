package com.example.mq.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


/**
 * @author: fds
 * @date: 2018/8/6
 * @description: mq配置文件
 */
@Configuration
public class MqConfig {

    public static final String EXCHANGE   = "spring-boot-exchange";
    public static final String ROUTINGKEY = "spring-boot-routingKey";

    @Value("${fds.rabbitmq.host}")
    private String rabbitmqHost;

    @Value("${fds.rabbitmq.username}")
    private String rabbitmqUsername;

    @Value("${fds.rabbitmq.password}")
    private String rabbitmqPassword;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(rabbitmqHost);
        connectionFactory.setUsername(rabbitmqUsername);
        connectionFactory.setPassword(rabbitmqPassword);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true); //必须要设置
        return connectionFactory;
    }

/*    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    // 必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }*/

    /**
     * 针对消费者配置
     * 1. 设置交换机类型
     * 2. 将队列绑定到交换机
     *
     *
     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     HeadersExchange ：通过添加属性key-value匹配
     DirectExchange:按照routingkey分发到指定队列
     TopicExchange:多关键字匹配
     */
/*    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE);
    }*/

    @Bean
    public Queue queue() {

        // 创建spring-boot-queue队列 并且持久化
        return new Queue("fds-queue", true);

    }

/*    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(MqConfig.ROUTINGKEY);
    }*/

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());

        // 开启手动 ack确认消息消费
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }
}
