package top.inson.rabbitmq;


import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import top.inson.rabbitmq.enums.UserTypeEnum;
import top.inson.rabbitmq.mq.MqSender;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private DataSource dataSource;


    private final Gson gson = new GsonBuilder().create();
    @Test
    public void testData(){
        log.info("data:" + dataSource);
        int code = UserTypeEnum.STUDENT.getCode();
        String desc = UserTypeEnum.STUDENT.getDesc();
        log.info("code:" + code);
    }

    @Test
    public void testValue(){
        log.info("queue:" + rabbitmqConstant.getQueueName());

    }

    @Test
    public void testSendMsg(){
        Map<String, Object> message = Maps.newHashMap();

        message.put("payOrderId", "202011301506057101006912290757");
        message.put("status", "success");
        log.info("发送到消息队列：{}", gson.toJson(message));

        mqSender.send(rabbitmqConstant.getExchangeName(), rabbitmqConstant.getRoutingKey(), gson.toJson(message));
        mqSender.send(rabbitmqConstant.getDelayExchange(), rabbitmqConstant.getDelayRoutingKey(), gson.toJson(message), 1000);
    }

    @Test
    public void testTkMybatis(){
        Example example = new Example(Users.class);
        example.createCriteria()
                .andEqualTo("account", "jingjitree");
        List<Users> users = usersMapper.selectByExample(example);
        log.info("查询到的数据：{}", gson.toJson(users));

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
