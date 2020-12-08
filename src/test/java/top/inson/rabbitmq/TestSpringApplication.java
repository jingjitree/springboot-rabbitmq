package top.inson.rabbitmq;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;
import top.inson.rabbitmq.constants.RabbitmqConstant;
import top.inson.rabbitmq.dao.IUsersMapper;
import top.inson.rabbitmq.entity.Users;
import top.inson.rabbitmq.mq.MqSender;

import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSpringApplication {
    @Autowired
    private RabbitmqConstant rabbitmqConstant;
    @Autowired
    private MqSender mqSender;
    @Autowired
    private IUsersMapper usersMapper;
    @Autowired
    private ApplicationContext context;



    @Test
    public void testValue(){
        log.info("queue:" + rabbitmqConstant.getQueueName());

    }

    @Test
    public void testSendMsg(){
        JSONObject message = new JSONObject();
        message.put("payOrderId", "202011301506057101006912290757");
        message.put("status", "success");
        log.info("发送到消息队列：{}", message.toJSONString());

        mqSender.send(rabbitmqConstant.getExchangeName(), rabbitmqConstant.getRoutingKey(), message.toJSONString());
        mqSender.send(rabbitmqConstant.getDelayExchange(), rabbitmqConstant.getDelayRoutingKey(), message.toString(), 1000);
    }

    @Test
    public void testTkMybatis(){
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("account", "jingjitree");
        List<Users> users = usersMapper.selectByExample(example);
        log.info("查询到的数据：{}", JSON.toJSONString(users));

    }

    @Test
    public void testCaching(){
        Users users = context.getBean(Users.class);
        users.setId(111);
        users.setAccount("jingjitree");
        log.info("users:{}", users.getAccount());
        log.info("account:" + users.getAccount());
    }

}
