package com.direa.serviceb;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RefreshScope
@Service
public class RemoteServiceImpl implements RemoteService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getFallback")
    public String getA(String serviceA_Id) {
        return this.restTemplate.getForObject("http://service-a/service-a/"+serviceA_Id, String.class);
    }



    public String getFallback(){
        return "fallback@!";
    }
}
