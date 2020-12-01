package com.direa.servicea;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@Component
public class WelcomeRemoteServiceImpl implements WelcomeRemoteService {

    /*
    *   config refresh :: curl -x post http://localhost:[port]/actuator/refresh
     */

    @Value("${test2.message.detail}")
    private String message;

    private final RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(WelcomeRemoteServiceImpl.class);



    public WelcomeRemoteServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    @HystrixCommand(commandKey = "veryWelcome",fallbackMethod = "getWelcomeFallback"
//            ,
//            commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
//            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
//    }
    )
    public String veryWelcome(String username) {
        System.out.println("Welcome To Hello World!");
        logger.info(String.format("Logger>>>>>>>> Very Welcome Method Called!"));
        return this.restTemplate.getForObject("http://service-b/welcome/"+username, String.class);
    }

    public String getWelcomeFallback(String username, Throwable t){
        logger.warn(String.format("Logger>>>>>>>> Exception="+t));

        return message;

    }

}

