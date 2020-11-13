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


    @GetMapping(path="/getAservice/{username}")
    public String getGreeting(@PathVariable String username){

        return remoteService.getA(username);
    }

}
