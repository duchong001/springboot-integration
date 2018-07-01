package com.dc.sb.web;

import com.dc.sb.config.DruidProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties({DruidProperties.class})
@MapperScan(basePackages = "com.dc.sb.dao")
@ComponentScan("com.dc.sb.*")
public class SbWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbWebApplication.class, args);
	}
}
