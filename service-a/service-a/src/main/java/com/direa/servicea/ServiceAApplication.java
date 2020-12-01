package com.direa.servicea;

import com.direa.servicea.configuration.CloudConfig;
import com.direa.servicea.service.WelcomeRemoteService;
import com.direa.servicea.service.WelcomeRemoteServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
@EnableConfigurationProperties(CloudConfig.class)
public class ServiceAApplication {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WelcomeRemoteService welcomeRemoteService(){
		return new WelcomeRemoteServiceImpl(restTemplate());
	}



	public static void main(String[] args) {
		SpringApplication.run(ServiceAApplication.class, args);
	}


}
