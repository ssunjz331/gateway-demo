package com.direa.servicea;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class AServiceController {


    @GetMapping(path="/{username}")
    public String greeting(@PathVariable ("username") String username){

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        throw new RuntimeException("I/O Exception");

        return String.format("Hello %s! \n", username);
//        return " 정상적인 Service A: "+ serviceA_Id;
    }

//    @Value("${example.phase}")
//    private String config;
//
//    @GetMapping
//    public String test() {
//        return config;}



//    @GetMapping("/AInfo")
//    public String getAInfo(){
////        throw new RuntimeException();
//        return "정상적인 A 서비스";
//
//    }


}
