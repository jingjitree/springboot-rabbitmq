package top.inson.rabbitmq.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserTypeEnum implements BaseEnum<UserTypeEnum, Integer>{
    STUDENT(1, "学生"),
    TEACHER(2, "教师")
    ;

    private final Integer code;
    private final String desc;

}
