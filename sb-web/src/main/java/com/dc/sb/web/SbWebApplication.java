package com.dc.sb.web;

import com.dc.sb.config.DruidProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@EnableConfigurationProperties({DruidProperties.class})
@MapperScan(basePackages = "com.dc.sb.dao")
@ComponentScan("com.dc.sb.*")
//@EnableDubboConfiguration
@EnableNeo4jRepositories
@EntityScan
public class SbWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbWebApplication.class, args);
	}
}
