package top.inson.rabbitmq.constants;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("trade.queue")
public class RabbitmqConstant {

    private String delayQueue;
    private String delayExchange;
    private String delayRoutingKey;
    private String queueName;
    private String exchangeName;
    private String routingKey;


}
