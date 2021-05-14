package top.inson.rabbitmq.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Table;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "users")
public class Users extends BaseEntity<Users> implements java.io.Serializable{

    private String username;
    private String password;
    private String account;
    private Integer sex;
    private String phone;
    private String address;

    private Integer userType;


}
