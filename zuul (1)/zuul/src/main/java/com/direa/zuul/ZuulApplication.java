package com.direa.zuul;

import com.direa.zuul.filter.PostFilter;
import com.direa.zuul.filter.PreFilter;
import com.direa.zuul.filter.RouteFilter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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

//class MyFallbackProvider implements FallbackProvider{
//
//	@Override
//	public String getRoute() {
//		return "*";
//	}
//
//	@Override
//	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
//		return new ClientHttpResponse() {
//			@Override
//			public HttpStatus getStatusCode() throws IOException {
//				return HttpStatus.OK;
//			}
//
//			@Override
//			public int getRawStatusCode() throws IOException {
//				return 200;
//			}
//
//			@Override
//			public String getStatusText() throws IOException {
//				return "OK";
//			}
//
//			@Override
//			public void close() {
//
//			}
//
//			@Override
//			public InputStream getBody() throws IOException {
//				return new ByteArrayInputStream("fallback".getBytes());
//			}
//
//			@Override
//			public HttpHeaders getHeaders() {
//				HttpHeaders headers = new HttpHeaders();
//				headers.setContentType(MediaType.APPLICATION_JSON);
//				return headers;
//			}
//		};
//	}
//}