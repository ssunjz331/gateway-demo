package com.direa.zuul;

import com.direa.zuul.filter.*;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.*;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;

import com.netflix.zuul.ZuulFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.web.util.UrlPathHelper;


@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableHystrixDashboard
public class ZuulApplication {

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

//	@Bean
//	public PlanService planService(){
//		return new PlanService();
//	}

//	private static final UrlPathHelper URL_PATH_HELPER = new UrlPathHelper();
//
//	@Bean
//	public ZuulFilter PostFilter2(final RateLimiter rateLimiter, final RateLimitProperties rateLimitProperties,
//								  final RouteLocator routeLocator, final RateLimitKeyGenerator rateLimitKeyGenerator,
//								  final RateLimitUtils rateLimitUtils) {
//		return new PostFilter2(rateLimitProperties, routeLocator, URL_PATH_HELPER, rateLimiter,
//				rateLimitKeyGenerator, rateLimitUtils);
//	}




	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}


	//when 3rd party application fails,
//	@Bean
//	public RateLimiterErrorHandler rateLimitErrorHandler() {
//		return new DefaultRateLimiterErrorHandler() {
//			@Override
//			public void handleSaveError(String key, Exception e) {
//				// implementation
//			}
//
//			@Override
//			public void handleFetchError(String key, Exception e) {
//				// implementation
//			}
//
//			@Override
//			public void handleError(String msg, Exception e) {
//				// implementation
//			}
//		};
//	}

}

