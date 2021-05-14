package top.inson.rabbitmq.web;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.inson.rabbitmq.entity.Users;
import top.inson.rabbitmq.service.IHelloService;


@Slf4j
@RestController
public class HelloWorldController {
    @Autowired
    private IHelloService helloService;


    private final Gson gson = new GsonBuilder().create();

    @GetMapping("/hello")
    public String hello(@RequestParam String username){
        Users users = helloService.queryById(1);
        log.info("userType:" + users.getUserType());

        log.info("users:{}", gson.toJson(users));

        return username + "您好，世界";
    }


}
