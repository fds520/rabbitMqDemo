package com.example.mq.component;

import com.example.mq.model.InfoModel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: fds
 * @date: 2018/8/6
 * @description: 消费者一对多
 */
@Component
@RabbitListener(queues = "testOneToMany")
public class CustomerOneToMany2 {

    @RabbitHandler
    public void process(InfoModel infoModel) {
        System.out.println("测试mq消费者一对多2  : " + infoModel);
    }
}
