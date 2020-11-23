package com.direa.serviceb;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RefreshScope
@RestController
public class BServiceController {

    //수정 중
    @GetMapping(path = "/test")
    public String greeting(@RequestParam("testNo") String testNo, HttpServletRequest request){

        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String name = (String) headerNames.nextElement();
            String value = request.getHeader(name);

            System.out.println(value);
        }



        return String.format("B Test %s! \n", testNo);
    }

    @GetMapping("/welcome/{username}")
    public String welcome(@PathVariable String username) {
        //Fallback Check
//        throw new RuntimeException("I/O Exception");
        return "Welcome To Hello World! \t"+username;
    }




}
