package com.example.mq.component;

import com.example.mq.model.UserInfo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: fds
 * @date: 2018/8/6
 * @description: 描述
 */
@Component
public class HelloSend {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 一对一队列发送
     */
    public void sendOneToOne() {
        System.out.println("一对一测试mq消息生产" + new Date().getTime());
        UserInfo userInfo = new UserInfo();
        userInfo.setCode("一对一测试mq消息生产");
        userInfo.setName("东野圭吾");
        userInfo.setCreateDate(new Date());
        List<String> jobs = new ArrayList<>();
        jobs.add("乒乓蛋");
        jobs.add("足球");
        userInfo.setJobs(jobs);
        this.rabbitTemplate.convertAndSend("testOneToOne", userInfo);
    }

    /**
     * 一对多队列发送
     */
    public void sendOneToMany() {
        System.out.println("一对多测试mq消息生产" + new Date().getTime());
        UserInfo userInfo = new UserInfo();
        userInfo.setCode("一对多测试mq消息生产");
        userInfo.setName("东野圭吾");
        userInfo.setCreateDate(new Date());
        List<String> jobs = new ArrayList<>();
        jobs.add("乒乓蛋");
        jobs.add("足球");
        userInfo.setJobs(jobs);
        this.rabbitTemplate.convertAndSend("testOneToMany", userInfo);
    }

    /**
     * 多对多队列发送
     */
    public void sendManyToMany() {
        System.out.println("多对多测试mq消息生产" + new Date().getTime());
        UserInfo userInfo = new UserInfo();
        userInfo.setCode("多对多测试mq消息生产");
        userInfo.setName("东野圭吾");
        userInfo.setCreateDate(new Date());
        List<String> jobs = new ArrayList<>();
        jobs.add("乒乓蛋");
        jobs.add("足球");
        userInfo.setJobs(jobs);
        this.rabbitTemplate.convertAndSend("testManyToMany", userInfo);
    }
}
