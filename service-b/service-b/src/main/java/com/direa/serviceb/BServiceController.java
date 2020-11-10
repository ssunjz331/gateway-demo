package com.direa.serviceb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/second")
public class BServiceController {

    @Autowired
    private RemoteService remoteService;

    @GetMapping(path="/{serviceB_Id}")
    public String ServiceB(@PathVariable String serviceB_Id){
        String Adetail = getA();
//        return "Service B: "+serviceB_Id;
        return remoteService.getA(serviceB_Id);
    }

    private String getA(){
        return new RemoteServiceImpl().getA("12345");
    }





}
