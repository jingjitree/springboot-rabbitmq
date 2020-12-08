package top.inson.rabbitmq.mq;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class MqSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;


    /**
     * 发送消息到队列
     * @param exchange
     * @param routingKey
     * @param message
     */
    public void send(String exchange, String routingKey, String message){
        log.info("发送到消息队列的消息体：{}", message);
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationId);
    }

    /**
     * 发送消息到延迟消息队列
     * @param delayExchange
     * @param delayRoutingKey
     * @param message
     * @param delayTime
     */
    public void send(String delayExchange, String delayRoutingKey, String message, long delayTime){
        log.info("发送消息到延迟消息队列：消息体：{}，延时时间：{}", message, delayTime);
        /**
         * jdk1.7及以下的写法
        rabbitTemplate.convertAndSend(delayExchange, delayQueue, message, new MessagePostProcessor(){
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(String.valueOf(delayTime));
                return message;
            }
        });
         */
        //jdk1.8的写法
        rabbitTemplate.convertAndSend(delayExchange, delayRoutingKey, message, msg -> {
            msg.getMessageProperties().setExpiration(String.valueOf(delayTime));
            return msg;
        });
    }



}
