package com.example.mq.controller;

import com.example.mq.component.HelloSend1;
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
    private HelloSend1 helloSend1;

    @PostMapping("/hello")
    public void hello() {
        helloSend1.send();
    }
}
