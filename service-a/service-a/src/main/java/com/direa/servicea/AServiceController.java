package com.direa.servicea;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.Socket;

@RestController
@RefreshScope
public class AServiceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public WelcomeRemoteService welcomeRemoteService;

    @GetMapping(path="/hi/{username}")
    public String greeting(@PathVariable ("username") String username){
        return String.format("Hello %s! \n", username);
    }

    @GetMapping("/simple")
    public ResponseEntity<String> getSimple() {
        return ResponseEntity.ok("Hi!!!!");
    }

    @GetMapping("/advanced")
    public ResponseEntity<String> getAdvanced() {
        return ResponseEntity.ok("Hello, how you doing?");
    }


    @GetMapping("/welcome/{username}")
    public String welcome(@PathVariable ("username") String username) {
        return welcomeRemoteService.veryWelcome(username);
    }

    //Dynamic Routing Check
    @GetMapping(path="/microservices/info")
    public String serviceInstance(){

        Socket socket = new Socket();

        try {
            System.out.println(InetAddress.getLocalHost().getHostName());
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            System.out.println(InetAddress.getLocalHost().getAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "done";
    }

}
