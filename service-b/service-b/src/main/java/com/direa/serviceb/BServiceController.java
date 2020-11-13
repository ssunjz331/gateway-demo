package com.direa.serviceb;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
public class BServiceController {
    private final RemoteService remoteService;

    public BServiceController(RemoteService remoteService) {
        this.remoteService = remoteService;
    }


    @GetMapping("/getGreeting/{username}")
    public String getGreeting(@PathVariable String username){

        return remoteService.getA(username);
    }

    @GetMapping(path = "/test/{testNum}")
    public String greeting(@PathVariable ("testNum") String testNum){

        return String.format("B Test %s! \n", testNum);
    }

}
