package com.direa.serviceb;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RemoteServiceImpl implements RemoteService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getFallback")
    public String getA(String serviceA_Id) {
        return this.restTemplate.getForObject("http://aservice/first/"+serviceA_Id, String.class);
    }


    public String getFallback(String serviceA_Id, Throwable t){
        System.out.println("t= "+t);
        return "fallback@!";
    }
}
