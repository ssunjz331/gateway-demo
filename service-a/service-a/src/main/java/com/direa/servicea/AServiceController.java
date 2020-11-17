package com.direa.servicea;


import org.springframework.cloud.client.discovery.DiscoveryClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.net.Socket;

@RestController
@RefreshScope
public class AServiceController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping(path="hi/{username}")
    public String greeting(@PathVariable ("username") String username){


//        throw new RuntimeException("I/O Exception");

        return String.format("Hello %s! \n", username);
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

//    @HystrixCommand(fallbackMethod = "getServiceInfoFallback")
//    @RequestMapping(value = "/story/service/info/{applicationName}", method = RequestMethod.GET)
//    public String serviceInstance(@PathVariable String applicationName) {
//
//        try{
//
//            Optional <List<ServiceInstance>> maybeServiceInstance = Optional.of(this.discoveryClient.getInstances(applicationName));
//
//            Function<String,String> makeResult = result -> result;
//
//            ServiceInstance service = maybeServiceInstance.get().get(0);
//
//            return makeResult.apply("ServiceID: " + service.getServiceId()+
//                    ", Host: " + service.getHost()+
//                    ", Port: " + Integer.toString(service.getPort()));
//
//        }catch(Exception e)
//        {
//            return "Cannot Found Instance " + applicationName;
//        }
//    }
//
//    public String getServiceInfoFallback(@PathVariable String applicationName)
//    {
//        return "Default Value";
//    }


}
