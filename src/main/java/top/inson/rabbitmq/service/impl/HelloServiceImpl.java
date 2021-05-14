package top.inson.rabbitmq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.inson.rabbitmq.dao.IUsersMapper;
import top.inson.rabbitmq.entity.Users;
import top.inson.rabbitmq.service.IHelloService;


@Service
public class HelloServiceImpl implements IHelloService {
    @Autowired
    private IUsersMapper usersMapper;


    @Override
    public Users queryById(Integer id) {
        return usersMapper.selectByPrimaryKey(id);
    }
}
