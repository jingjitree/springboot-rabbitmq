package top.inson.rabbitmq.enums;

public interface BaseEnum<E extends Enum<?>, T> {

    T getCode();

    String getDesc();
}
