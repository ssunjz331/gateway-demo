package com.direa.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import io.github.bucket4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/*
    prefilter - 주로 logging이나 인증 기능.
    logging is possible in this project.
 */

public class PreFilter extends ZuulFilter {
    private static Logger logger =  LoggerFactory.getLogger(PreFilter.class);
    private RestTemplate restTemplate = new RestTemplate();

    private RateLimiter rateLimiter = RateLimiter.create(.1);


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {

//        boolean isValid = true;
//        return authorizationHeader.equals("x-header");
//        String uri = request.getRequestURI();
//            boolean check = false;
//
//            try{
//                URL tempUrl  = new URL(uri);
//                HttpURLConnection connection = (HttpURLConnection)tempUrl.openConnection();
//                connection.setRequestMethod("GET");
//                connection.connect();
//
//                if(200 == connection.getResponseCode()) {
//
//                    check = true;
//                }
//
//                return check;
//            }catch(IOException e){
//                return false;
//            }






        return true;
    }

    @Override
    public Object run() throws ZuulException {
//    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

        //uri 오타 시 error 수정중
//       String uri = request.getRequestURI();
//        System.out.println("uri : "+uri);
////
//        if(uri=="/service-b/test") {
//            logger.info("uri"+uri);
//            if(request.getParameter("testNo")==null){
//                logger.warn("missing params : "+uri);
//                throw new ZuulException("missing parameter : ", 400,  "missing \"testNo\"");
//            }
//        }


        //
//        RequestContext currentContext = RequestContext.getCurrentContext();


//        if (!rateLimiter.tryAcquire()) {
//            currentContext.setSendZuulResponse(false);
//            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
//        }



        System.out.println("pre");
        logger.info("Logger>>>>>> PreFilter");
        logger.info(String.format("Logger>>>>>> %s request to %s", request.getMethod(), request.getRequestURL().toString()));


        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!validateToken(authorizationHeader)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("API key not authorized");
            ctx.getResponse().setHeader("Content-Type", "text/plain;charset=UTF-8");
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
        }

        //
//        Object accessToken=request.getParameter("token");
//        if (null==accessToken)
//        {
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseStatusCode(404);
//            try {
//                ctx.getResponse().getWriter().write("token is empty");
//
//            }catch (Exception e) { }
//            return null;
//        }


        return null;
    }



    private boolean validateToken(String tokenHeader) {
        // do something to validate the token
        return true;
    }



}
