package com.direa.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


public class PreFilter extends ZuulFilter {
    private static Logger logger =  LoggerFactory.getLogger(PreFilter.class);
    private RestTemplate restTemplate = new RestTemplate();




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

        String uri = request.getRequestURI();
        System.out.println("uri : "+uri);
        System.out.println("request.getHeaderNames : "+request.getHeaderNames());

        String username = "username";
        System.out.println(request.getParameter(username));


        System.out.println("pre");
        logger.info("Logger>>>>>> PreFilter");
        logger.info(String.format("Logger>>>>>> %s request to %s", request.getMethod(), request.getRequestURL().toString()));







        return null;
    }






}
