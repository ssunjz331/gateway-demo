package com.direa.serviceb;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteServiceImpl implements RemoteService {

    private final RestTemplate restTemplate;

    public RemoteServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallback")
    public String getAServiceInfo(String serviceA_Id) {
        return this.restTemplate.getForObject("http://a/service-a/"+serviceA_Id,String.class);
    }


    public String fallback(String serviceA_Id, Throwable t){
        System.out.println("t= "+t);
        return "fallback@!";
    }
}
