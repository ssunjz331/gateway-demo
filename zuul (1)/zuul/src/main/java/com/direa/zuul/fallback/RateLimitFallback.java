//package com.direa.zuul.fallback;
//
//
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitExceededException;
//
//import com.netflix.zuul.context.RequestContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.client.ClientHttpResponse;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.*;
//
//public class RateLimitFallback implements FallbackProvider {
//
//    private static final String DEFAULT_MESSAGE = "Service not available."; //RateLimit 에러
//    private Logger logger = LoggerFactory.getLogger(RateLimitFallback.class);
//
//    @Override
//    public String getRoute() {
//        return null;
//    }
//
//    @Override
//    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
//        if (cause instanceof RateLimitExceededException) {
//
//            RequestContext ctx = RequestContext.getCurrentContext();
//            HttpServletRequest request = ctx.getRequest();
//            HttpServletResponse response = ctx.getResponse();
//
//            HttpHeaders headers = (HttpHeaders) response.getHeaderNames();
//            logger.info("Logger>>>>> RateLimit: "+headers.getFirst(HEADER_LIMIT));
//            logger.info("Logger>>>>> RateLimitRemaining: "+headers.getFirst(HEADER_REMAINING));
//
//            return new GatewayClientResponse(HttpStatus.TOO_MANY_REQUESTS, DEFAULT_MESSAGE);
//        } else {
//            return new GatewayClientResponse(HttpStatus.INTERNAL_SERVER_ERROR, DEFAULT_MESSAGE);
//        }
//    }
//}
