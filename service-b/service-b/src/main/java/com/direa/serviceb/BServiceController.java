package com.direa.serviceb;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
public class BServiceController {


    @GetMapping(path = "/test/{testNum}")
    public String greeting(@PathVariable ("testNum") String testNum){

        return String.format("B Test %s! \n", testNum);
    }

    @GetMapping("/welcome/{username}")
    public String welcome(@PathVariable String username) {
        //Fallback Check
//        throw new RuntimeException("I/O Exception");
        return "Welcome To Hello World!"+username;
    }
}
