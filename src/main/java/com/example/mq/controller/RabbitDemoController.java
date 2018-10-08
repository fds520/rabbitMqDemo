package com.example.mq.controller;

import com.example.mq.component.ProducerDemo;
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
public class RabbitDemoController {

    /**
     *
     */
    @Autowired
    private ProducerDemo producerDemo;

    /**
     * 单生产者和单消费者
     */
    @PostMapping("/demo")
    public void oneToOne() {
        producerDemo.sendOneToOne();
    }
}
