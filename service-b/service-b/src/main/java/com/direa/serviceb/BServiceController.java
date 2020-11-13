package com.direa.serviceb;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
//@RequestMapping("/second")
public class BServiceController {


    private final RemoteService remoteService;

    public BServiceController(RemoteService remoteService) {
        this.remoteService = remoteService;
    }

//    @GetMapping(path="/{serviceB_Id}")
//    public String ServiceB(@PathVariable String serviceB_Id){
//        String Adetail = getA();
////        return "Service B: "+serviceB_Id;
//        return remoteService.getA(serviceB_Id);
//    }

//    private String getA(){
//        return new RemoteServiceImpl().getA("111");
//    }


//    @GetMapping("/get-A/{username}")
//    public String getA(Model model, @PathVariable("username") String username){
//        model.addAttribute("greeting", remoteService.getA(username));
//        return "greeting-view";
//    }

    @GetMapping(path="/getAservice/{username}")
    public String getGreeting(@PathVariable String username){

        return remoteService.getA(username);
    }


//    @RequestMapping("/table")
//    @ResponseStatus(HttpStatus.OK)
//    public String table(){
//        //테스트
//        return remoteService.table();
//    }




}
