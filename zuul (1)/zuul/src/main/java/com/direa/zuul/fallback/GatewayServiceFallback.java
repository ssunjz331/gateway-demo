//package com.direa.zuul.fallback;
//
//import com.netflix.hystrix.exception.HystrixTimeoutException;
//import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.stereotype.Component;
//
//@Component
//class GatewayServiceFallback implements FallbackProvider {
//
//    private static final String DEFAULT_MESSAGE = "Service not available."; //Fallback for Routing Error
//
//    @Override
//    public String getRoute() {
//        return "*"; // or return null;
//    }
//
//    @Override
//    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
//        if (cause instanceof HystrixTimeoutException) {
//            String.format("NOT AVAILABLE \n", HttpStatus.GATEWAY_TIMEOUT);
//            return new GatewayClientResponse(HttpStatus.GATEWAY_TIMEOUT, DEFAULT_MESSAGE);
//        } else {
//            String.format("NOT AVAILABLE \n", HttpStatus.INTERNAL_SERVER_ERROR);
//            return new GatewayClientResponse(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_MESSAGE);
//        }
//    }
//
//}
//
