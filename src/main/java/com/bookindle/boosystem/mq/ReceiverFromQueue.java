package com.bookindle.boosystem.mq;

import com.bookindle.boosystem.util.Api.SendTelephoneMsg;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author longzhonghua
 * @data 2019/02/03 11:07
 */
@Component
@RabbitListener(queues = "Queue1")//监听QueueHello的消息队列
public class ReceiverFromQueue {
    @RabbitHandler//@RabbitHandler来实现具体消费
    public void QueueReceiver(Map<String, String> Queue1) {
//        System.out.println("Receiver A: " + Queue1);
        SendTelephoneMsg sendTelephoneMsg = new SendTelephoneMsg();
        sendTelephoneMsg.sendMsgToUserMobile(Queue1.get("mobile"), Queue1.get("content"));
    }

}