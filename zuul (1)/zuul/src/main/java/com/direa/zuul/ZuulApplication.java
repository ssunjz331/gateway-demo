package com.direa.zuul;

import com.direa.zuul.filter.*;


import com.netflix.zuul.ZuulFilter;
import io.github.bucket4j.grid.GridBucketState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;


@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
@EnableHystrixDashboard
public class ZuulApplication {

	@Autowired
	private ServerProperties serverProperties;
	@Autowired
	private ZuulProperties zuulProperties;

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

	@Bean
	@Qualifier("RateLimit")
	public Cache<String, GridBucketState> jCache() {
		CachingProvider cachingProvider = Caching.getCachingProvider();
		CacheManager cacheManager = cachingProvider.getCacheManager();
		MutableConfiguration<String, GridBucketState> config =
				new MutableConfiguration<String, GridBucketState>()
						.setTypes(String.class, GridBucketState.class)
						.setReadThrough(true);

		return cacheManager.createCache("RateLimit", config);
	}





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

