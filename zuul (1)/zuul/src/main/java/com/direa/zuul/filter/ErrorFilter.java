package com.direa.zuul.filter;

import com.direa.zuul.fallback.GatewayClientResponse;
//import com.direa.zuul.fallback.RateLimitFallback;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitExceededException;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.HEADER_LIMIT;
import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.HEADER_REMAINING;

public class ErrorFilter extends SendErrorFilter {
    private static final String DEFAULT_MESSAGE = "Service not available."; //RateLimit 에러
    private Logger logger = LoggerFactory.getLogger(ErrorFilter.class);


    protected static final String SEND_ERROR_FILTER_RAN = "sendErrorFilter.ran";

    @Value("${error.path:/error}")
    private String errorPath;


    @Override
    public String filterType() {
        return "error";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // only forward to errorPath if it hasn't been forwarded to already

//
//        System.out.println("ctx.getThrowable.getCause? : "+ctx.getThrowable().getCause());
//        System.out.println("ctx.getThrowable.getCause? : "+ctx.getThrowable().getCause().getClass());
//        System.out.println("ctx.getThrowable.getCause? : "+ctx.getThrowable().getCause().equals(HttpStatus.TOO_MANY_REQUESTS));
//        if (ctx.getThrowable().getCause() instanceof RateLimitExceededException==true) {
//        System.out.println("HttpStatus.TOO_MANY_REQUESTS? : "+HttpStatus.TOO_MANY_REQUESTS);}


//        return ctx.getThrowable() != null
//                && !ctx.getBoolean(SEND_ERROR_FILTER_RAN, false);
        return ctx.getThrowable().getCause() instanceof RateLimitExceededException;
//        return RequestContext.getCurrentContext().containsKey(RateLimitConstants.RATE_LIMIT_EXCEEDED);

    }

    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

//        System.out.println("에러필터");
//        logger.info("Logger>>>>> RateLimit: "+response.getHeader(HEADER_LIMIT));
//        logger.info("Logger>>>>> RateLimitRemaining: "+response.getHeader(HEADER_REMAINING));

        //
        try {
//            RequestContext ctx = RequestContext.getCurrentContext();
            ExceptionHolder exception = findZuulException(ctx.getThrowable());
//            HttpServletRequest request = ctx.getRequest();

            request.setAttribute("javax.servlet.error.status_code",
                    exception.getStatusCode());

            logger.warn("Error during filtering-RateLimit Exceeded!!!!", exception.getThrowable()); //RateLimit 시 에러 메시지 로그

            request.setAttribute("javax.servlet.error.exception",
                    exception.getThrowable());

            if (StringUtils.hasText(exception.getErrorCause())) {
                request.setAttribute("javax.servlet.error.message",
                        exception.getErrorCause());
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(this.errorPath);
            if (dispatcher != null) {
                ctx.set(SEND_ERROR_FILTER_RAN, true);
                if (!ctx.getResponse().isCommitted()) {
                    ctx.setResponseStatusCode(exception.getStatusCode());
                    dispatcher.forward(request, ctx.getResponse());
                }
            }
        }
        catch (Exception ex) {
            ReflectionUtils.rethrowRuntimeException(ex);
        }





        return null;
    }


//    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
//        if (cause instanceof RateLimitExceededException) {
//
//            RequestContext ctx = RequestContext.getCurrentContext();
//            HttpServletRequest request = ctx.getRequest();
//            HttpServletResponse response = ctx.getResponse();
//
//            HttpHeaders headers = (HttpHeaders) response.getHeaderNames();
//            logger.error("Logger>>>>> RateLimit: "+headers.getFirst(HEADER_LIMIT));
//            logger.error("Logger>>>>> RateLimitRemaining: "+headers.getFirst(HEADER_REMAINING));
//
//            return new GatewayClientResponse(HttpStatus.TOO_MANY_REQUESTS, DEFAULT_MESSAGE);
//        } else {
//            return new GatewayClientResponse(HttpStatus.BAD_REQUEST, DEFAULT_MESSAGE);
//        }
//    }
}
