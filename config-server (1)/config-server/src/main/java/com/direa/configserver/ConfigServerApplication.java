package com.direa.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
//@EnableAutoConfiguration
public class ConfigServerApplication {

	public static void main(String[] args) {
//		System.setProperty("spring.profiles.default", "local");
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
