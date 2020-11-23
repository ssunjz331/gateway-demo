package com.direa.servicea;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.Socket;

@RestController
@RefreshScope
public class AServiceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public WelcomeRemoteService welcomeRemoteService;

    //simple method
    @GetMapping(path="/hi/{username}")
    public String greeting(@PathVariable ("username") String username){
        return String.format("Hello %s! \n", username);
    }

//    @GetMapping("/simple")
//    public ResponseEntity<String> getSimple() {
//        return ResponseEntity.ok("Hi!!!!");
//    }
//
//    @GetMapping("/advanced")
//    public ResponseEntity<String> getAdvanced() {
//        return ResponseEntity.ok("Hello, how you doing?");
//    }

    //service-b호출
    @GetMapping("/welcome/{username}")
    public String welcome(@PathVariable ("username") String username) {
        return welcomeRemoteService.veryWelcome(username);
    }

    //Dynamic Routing Check
    @GetMapping(path="/microservices/info")
    public String serviceInstance(){

        Socket socket = new Socket();

        try {

            return "Host Name: "+InetAddress.getLocalHost().getHostName() + ", Host Address: "+InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "done";
        }

    }

}
