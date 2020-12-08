package top.inson.rabbitmq.core;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.inson.rabbitmq.constants.RabbitmqConstant;

import java.util.Map;

@Slf4j
@Configuration
public class RabbitmqConfiguration {
    @Autowired
    private RabbitmqConstant mqConstant;


    @Bean
    public RabbitTemplate rabbitTemplate(CachingConnectionFactory connectionFactory){
        return new RabbitTemplate(connectionFactory);
    }

    //三步骤：1.创建队列，2.创建交换机，3.绑定队列和交换机
    @Bean
    public Queue queue(){
        return new Queue(mqConstant.getQueueName());
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(mqConstant.getExchangeName());
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(topicExchange()).with(mqConstant.getRoutingKey());
    }

    @Bean
    public DirectExchange delayExchange(){
        return new DirectExchange(mqConstant.getDelayExchange());
    }

    @Bean
    public Queue delayQueue(){
        Map<String,Object> params = Maps.newHashMap();
        params.put("x-dead-letter-exchange", mqConstant.getDelayExchange());
        params.put("x-dead-letter-routing-key", mqConstant.getDelayRoutingKey());
        return new Queue(mqConstant.getDelayQueue(), true, false, false, params);
    }

    @Bean
    public Binding delayBinding(){
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(mqConstant.getDelayRoutingKey());
    }

}
