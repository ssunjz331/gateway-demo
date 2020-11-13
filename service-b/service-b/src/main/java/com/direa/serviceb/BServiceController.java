package com.direa.serviceb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
//@RequestMapping("/second")
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
        return new RemoteServiceImpl().getA("111");
    }

//    @RequestMapping("/table")
//    @ResponseStatus(HttpStatus.OK)
//    public String table(){
//        //테스트
//        return remoteService.table();
//    }




}
