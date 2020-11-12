package com.direa.servicea;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
//@RequestMapping("/first")
public class AServiceController {

//    @Value("$who.am.i")
//    private String identity;
//
//    @GetMapping("/test")
//    public String test(){
//        return identity;
//    }

    @GetMapping(path="/{serviceA_Id}")
    public String ServiceA(@PathVariable String serviceA_Id){
        return " 정상적인 Service A: "+ serviceA_Id;
    }

//    @GetMapping("/AInfo")
//    public String getAInfo(){
////        throw new RuntimeException();
//        return "정상적인 A 서비스";
//
//    }


}
