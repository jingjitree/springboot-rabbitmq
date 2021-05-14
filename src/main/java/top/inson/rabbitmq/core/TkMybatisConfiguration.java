package top.inson.rabbitmq.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan("top.inson.rabbitmq.dao")
@EnableTransactionManagement
public class TkMybatisConfiguration {




}
