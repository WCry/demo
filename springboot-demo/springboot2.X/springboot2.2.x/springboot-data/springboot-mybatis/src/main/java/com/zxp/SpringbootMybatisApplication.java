package com.zxp;

import org.apache.ibatis.binding.MapperProxy;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class SpringbootMybatisApplication {

	public static void main(String[] args) {
		SqlSession ds;
		ds.getMapper()
		SpringApplication.run(SpringbootMybatisApplication.class, args);
	}
}
