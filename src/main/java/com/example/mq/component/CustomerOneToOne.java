package com.example.mq.component;

import com.example.mq.model.UserInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: fds
 * @date: 2018/8/6
 * @description: 消费者一对一
 */
@Component
@RabbitListener(queues = "spring-boot-queue")
public class CustomerOneToOne {

    @RabbitHandler
    public void process(UserInfo userInfo) {
        System.out.println("测试mq消费者一对一  : " + userInfo);
    }
}
