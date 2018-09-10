package com.example.mq.component;

import com.example.mq.model.InfoModel;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: fds
 * @date: 2018/8/6
 * @description: 消费者一对一
 */
@Component
public class CustomerDemo {

    // 监听fds-queue

    /**
     *
     * @param order
     * @param channel
     * @param message
     */
    @RabbitListener(queues = "fds-queue")
    public void process(InfoModel order, Channel channel, Message message) {

        try {
            String a = null;
            a.split("123");
            System.out.println("测试mq消费者一对一  : " + order.toString());
            try {
                // 确认消息成功消费
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();

            // 取消消息消费
            /*try {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            } catch (IOException e1) {
                e1.printStackTrace();
            }*/
        }
    }
}
