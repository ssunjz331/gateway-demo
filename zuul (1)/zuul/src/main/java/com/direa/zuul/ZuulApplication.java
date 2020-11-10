package com.direa.zuul;

import com.direa.zuul.filter.PostFilter;
import com.direa.zuul.filter.PreFilter;
import com.direa.zuul.filter.RouteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@Bean
	public PreFilter preFilter(){
		return new PreFilter();
	}

	@Bean
	public RouteFilter routeFilter(){
		return new RouteFilter();
	}

	@Bean
	public PostFilter postFilter(){
		return new PostFilter();
	}


}
