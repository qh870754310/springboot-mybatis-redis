package com.qh.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * 城市实体类
 * Created by Administrator on 2017/9/18.
 */
@Getter
@Setter
@ToString
@Alias(value = "city")
//domain 对象 City 必须实现序列化，因为需要将对象序列化后存储到 Redis。如果没实现 Serializable ，控制台会爆出以下异常：
//Serializable
//   java.lang.IllegalArgumentException: DefaultSerializer requires a Serializable payload but received an object of type
public class City implements Serializable {

    /**
     * 城市标号
     */
    private Long id;
    /**
     * 省份编号
     */
    private Long provinceId;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 描述
     */
    private String description;
}
