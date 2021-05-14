package top.inson.rabbitmq;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import top.inson.rabbitmq.entity.Users;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class TestLambda {

    private final Gson gson = new GsonBuilder().create();
    @Test
    public void testList(){
        Users users = new Users();
        users.setAccount("jingjitree")
                .setPassword("123456")
                .setUsername("荆棘树")
                .setPhone("18002667160")
                .setSex(1)
                .setId(1);
        Users users2 = new Users();
        BeanUtils.copyProperties(users, users2);
        users2.setAccount("tree")
                .setUsername("小红")
                .setSex(0)
                .setId(2);
        Users users3 = new Users();
        users3.setAccount("tree2")
                .setUsername("小明")
                .setSex(3)
                .setId(3);

        log.info("users2:{}", gson.toJson(users2));

        List<Users> lists = Lists.newArrayList();
        lists.add(users);
        lists.add(users2);
        lists.add(users3);
        //distinct方法是去掉重复的
        List<Integer> ids = lists.stream().map(Users::getId).distinct().collect(Collectors.toList());
        log.info("ids:{}", ids);
        //需求：将性别相同的人分类
        Map<Integer, Users> usersMap = lists.stream().collect(Collectors.toMap(Users::getSex, u -> u));
        log.info("usersMap:{}", gson.toJson(usersMap));


    }




}
