package com.direa.servicea;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class WelcomeRemoteServiceImpl implements WelcomeRemoteService {

    private final RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(WelcomeRemoteServiceImpl.class);

    public WelcomeRemoteServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    @HystrixCommand(commandKey = "veryWelcome",fallbackMethod = "getWelcomeFallback",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
    })
    public String veryWelcome(String username) {
        System.out.println("Welcome To Hello World!");
        logger.info(String.format("Logger>>>>>>>> Very Welcome Method Called!"));
        return this.restTemplate.getForObject("http://service-b/welcome/"+username, String.class);
    }

    public String getWelcomeFallback(String username, Throwable t){
        logger.info(String.format("Logger>>>>>>>> Exception="+t));
        logger.info(String.format("Logger>>>>>>>> Fallback"));
        return "OMG, Fallback happened";
    }
}