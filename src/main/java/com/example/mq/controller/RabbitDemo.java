package com.example.mq.controller;

import com.example.mq.component.HelloSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fds
 * @date: 2018/8/6
 * @description: 描述
 */

@RestController
@RequestMapping("/rabbit")
public class RabbitDemo {

    @Autowired
    private HelloSend helloSend;

    /**
     * 单生产者和单消费者
     */
    @PostMapping("/oneToOne")
    public void oneToOne() {
        helloSend.sendOneToOne();
    }

    /**
     * 单生产者-多消费者
     */
    @PostMapping("/oneToMany")
    public void oneToMany() {
        for (int i = 0; i < 15; i++) {

            helloSend.sendOneToMany();
        }
    }

    /**
     * 多生产者-多消费者
     */
    @PostMapping("/manyToMany")
    public void manyToMany() {
        for (int i = 0; i < 15; i++) {

            helloSend.sendManyToMany1();
            helloSend.sendManyToMany2();
        }
    }
}
