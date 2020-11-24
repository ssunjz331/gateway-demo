//package com.direa.zuul.fallback;
//
//import com.netflix.hystrix.exception.HystrixTimeoutException;
//import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.client.ClientHttpResponse;
//import org.springframework.stereotype.Component;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
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
//
//        return new ClientHttpResponse() {
//            @Override
//            public HttpStatus getStatusCode() throws IOException {
//                return HttpStatus.OK;
//            }
//
//            @Override
//            public int getRawStatusCode() throws IOException {
//                return HttpStatus.OK.value();
//            }
//
//            @Override
//            public String getStatusText() throws IOException {
//                return HttpStatus.OK.toString();
//            }
//
//            @Override
//            public void close() {
//
//            }
//
//            @Override
//            public InputStream getBody() throws IOException {
//                return new ByteArrayInputStream("{\"factorA\":\"죄송합니다. 서비스가 중단되었습니다.\",\"factorB\":\"?\",\"id\":null}".getBytes());
//            }
//
//            @Override
//            public HttpHeaders getHeaders() {
//                HttpHeaders headers = new HttpHeaders();
//                headers.setContentType(MediaType.APPLICATION_JSON);
//                headers.setAccessControlAllowCredentials(true);
//                headers.setAccessControlAllowOrigin("*");
//                return headers;
//            }
//        };
//
//
////        if (cause instanceof HystrixTimeoutException) {
////            String.format("NOT AVAILABLE \n", HttpStatus.GATEWAY_TIMEOUT);
////            return new GatewayClientResponse(HttpStatus.GATEWAY_TIMEOUT, DEFAULT_MESSAGE);
////        } else {
////            String.format("NOT AVAILABLE \n", HttpStatus.INTERNAL_SERVER_ERROR);
////            return new GatewayClientResponse(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_MESSAGE);
////        }
//    }
//
//}
//
