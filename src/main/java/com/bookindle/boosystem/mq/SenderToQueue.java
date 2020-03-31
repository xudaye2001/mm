package com.bookindle.boosystem.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author longzhonghua
 * @data 2019/02/03 11:06
 * rabbitTemplate是springboot 提供的默认实现
 */
@Component
public class SenderToQueue {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(Map<String, String> context) {
//        System.out.println("Sender : " + context);
        //使用AmqpTemplate将消息发送到消息队列QueueHello中去
        rabbitTemplate.convertAndSend("Queue1", context);
    }

}