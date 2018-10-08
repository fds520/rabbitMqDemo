package com.example.mq.component;

import com.example.mq.model.InfoModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: fds
 * @date: 2018/8/6
 * @description: 生产消息组件
 */
@Component
public class ProducerDemo {

    /**
     *
     */
    private static Integer i = 1;

    /**
     *
     */
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 生产者发送消息
     */
    public void sendOneToOne() {
        System.out.println("一对一测试mq消息生产" + i);
        InfoModel userInfoModel = new InfoModel();

        userInfoModel.setCode("一对一测试mq消息生产" + i++);
        userInfoModel.setName("东野圭吾");
        userInfoModel.setCreateDate(new Date());
        List<String> jobs = new ArrayList<>();
        jobs.add("乒乓蛋");
        jobs.add("足球");
        userInfoModel.setJobs(jobs);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(userInfoModel);

            MessageProperties messageProperties = new MessageProperties();

            // 设置json格式传输
            messageProperties.setContentType("application/json");
            Message message = new Message(json.getBytes(), messageProperties);
            this.rabbitTemplate.convertAndSend("fds-queue", message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
