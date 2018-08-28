package com.example.mq.config;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author: fds
 * @date: 2018/8/28
 * @description: 描述

public class Test {
    public static void main(String[] args) throws IOException {

        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置RabbitMQ地址
        factory.setHost("101.132.133.81");
        factory.setUsername("guest");
        factory.setPassword("fdsmq");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        //创建一个新的连接
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        //创建一个通道
        Channel channel = connection.createChannel();
        //声明要关注的队列
        channel.queueDeclare("testOneToMany", Boolean.TRUE, false, false, null);
        System.out.println("Customer1 Waiting Received messages");
        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer1 Received '" + message + "'");
            }
        };


        channel.queueDeclare("testOneToOne", Boolean.TRUE, false, false, null);
        System.out.println("Customer2 Waiting Received messages");
        //DefaultConsumer类实现了Consumer接口，通过传入一个频道，
        // 告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer2 = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Customer2 Received '" + message + "'");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 手动回复队列应答 -- RabbitMQ中的消息确认机制
        channel.basicConsume("testOneToOne", false, consumer2);
    }
}
*/