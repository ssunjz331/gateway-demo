//package com.direa.zuul.filter;
//
//import com.direa.zuul.fallback.GatewayClientResponse;
////import com.direa.zuul.fallback.RateLimitFallback;
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants;
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitExceededException;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.client.ClientHttpResponse;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.HEADER_LIMIT;
//import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.HEADER_REMAINING;
//
//public class ErrorFilter extends SendErrorFilter {
//    private static final String DEFAULT_MESSAGE = "Service not available."; //RateLimit 에러
//    private Logger logger = LoggerFactory.getLogger(ErrorFilter.class);
//
//    @Override
//    public String filterType() {
//        return "error";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return RequestContext.getCurrentContext().containsKey(RateLimitConstants.RATE_LIMIT_EXCEEDED);
//    }
//
//    @Override
//    public Object run(){
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        HttpServletResponse response = ctx.getResponse();
//
//        System.out.println("에러필터");
//        logger.info("Logger>>>>> RateLimit: "+response.getHeader(HEADER_LIMIT));
//        logger.info("Logger>>>>> RateLimitRemaining: "+response.getHeader(HEADER_REMAINING));
//
//        return null;
//    }
//
//
////    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
////        if (cause instanceof RateLimitExceededException) {
////
////            RequestContext ctx = RequestContext.getCurrentContext();
////            HttpServletRequest request = ctx.getRequest();
////            HttpServletResponse response = ctx.getResponse();
////
////            HttpHeaders headers = (HttpHeaders) response.getHeaderNames();
////            logger.error("Logger>>>>> RateLimit: "+headers.getFirst(HEADER_LIMIT));
////            logger.error("Logger>>>>> RateLimitRemaining: "+headers.getFirst(HEADER_REMAINING));
////
////            return new GatewayClientResponse(HttpStatus.TOO_MANY_REQUESTS, DEFAULT_MESSAGE);
////        } else {
////            return new GatewayClientResponse(HttpStatus.BAD_REQUEST, DEFAULT_MESSAGE);
////        }
////    }
//}
