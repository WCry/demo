package com.gf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ES 对于文档预处理
 * https://www.felayman.com/articles/2017/11/24/1511527532643.html#b3_solo_h2_0
 * https://blog.csdn.net/chunqiqian1285/article/details/100977184
 * ES自动创建时间戳
 */

@SpringBootApplication
public class SpringbootElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootElasticsearchApplication.class, args);
	}
}
