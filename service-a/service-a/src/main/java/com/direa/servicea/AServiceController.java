package com.direa.servicea;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.Socket;

@RefreshScope
@RestController
public class AServiceController {

    @Value("${test.message}")
    private String testmessage;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public WelcomeRemoteService welcomeRemoteService;

    //simple method
    @GetMapping(path="/hi/{username}")
    public String greeting(@PathVariable ("username") String username){
        return String.format("Hello %s! \n", username);
    }

    //simple method
    @GetMapping("/atest")
    public String testt(){
        return testmessage;
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

    //refresh test

    @HystrixCommand(fallbackMethod = "getWelcomeFallback1")
    @GetMapping("/welcome1/{username}")
    public String veryWelcome1(@PathVariable ("username") String username1) {
//        RestTemplate restTemplate;
        System.out.println("Welcome To Hello World!");
        logger.info(String.format("Logger>>>>>>>> Very Welcome Method Called!"));
        return new RestTemplate().getForObject("http://service-b/welcome/"+username1, String.class);
    }

    @Value("${test2.message.detail}")
    private String message;

    public String getWelcomeFallback1(String username, Throwable t){
        logger.warn(String.format("Logger>>>>>>>> Exception="+t));

        return message;

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
