package top.inson.rabbitmq.core;

import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

@Configuration
@MapperScan("top.inson.rabbitmq.dao")
public class TkMybatisConfiguration {




}
