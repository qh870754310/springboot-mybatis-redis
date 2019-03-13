package com.qh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// mapper 接口类扫描包配置
@MapperScan("com.qh.dao")
public class SpringbootMybatisRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisRedisApplication.class, args);
	}
}
