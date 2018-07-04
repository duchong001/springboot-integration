package com.dc.sb.dubbo.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class SbDubboProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbDubboProviderApplication.class, args);
	}
}
