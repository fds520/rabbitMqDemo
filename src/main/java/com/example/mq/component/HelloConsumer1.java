package com.example.mq.component;

import com.example.mq.model.UserInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: fds
 * @date: 2018/8/6
 * @description: 描述
 */
@Component
@RabbitListener(queues = "hello")
public class HelloConsumer1 {

    @RabbitHandler
    public void process(UserInfo userInfo) {
        System.out.println("测试mq消息消费  : " + userInfo);
    }
}
