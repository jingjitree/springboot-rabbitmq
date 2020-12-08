package top.inson.rabbitmq.mq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;


@Slf4j
@Component
public class MqCustomer {



    @RabbitListener(queues = {"${trade.queue.queueName}"})
    public void queueListener(Message message){
        String msg = null;
        try {
            msg = new String(message.getBody(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("消息队列接收到消息体：{}", msg);
    }


    @RabbitListener(queues = {"${trade.queue.delayQueue}"})
    public void delayQueueListener(Message message){
        String msg = null;
        try {
            msg = new String(message.getBody(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        log.info("延迟消息队列接收到消息体：{}", msg);
    }

}
