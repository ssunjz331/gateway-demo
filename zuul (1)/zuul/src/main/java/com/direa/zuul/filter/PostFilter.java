package com.direa.zuul.filter;


import com.google.common.io.CharStreams;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.*;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitType;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitExceededException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;

import org.springframework.cloud.netflix.zuul.util.ZuulRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.*;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RefreshScope
public class PostFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(PostFilter.class);
    private RestTemplate restTemplate = new RestTemplate();





    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    @Override
    public boolean shouldFilter() {

        return !RequestContext.getCurrentContext().containsKey(RATE_LIMIT_EXCEEDED);
//        return true;
    }

    @Override
    public Object run() throws ZuulException {
//    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();


        try (final InputStream responseDataStream = context.getResponseDataStream()) {

            if (responseDataStream == null) {
                logger.info("BODY: {}", "");
                return null;
            }

            String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
            logger.info("BODY: {}", responseData);

            context.setResponseBody(responseData);

            //
//            int id = 1;
//            response.addHeader("X-Response-ID",String.valueOf(id++));
//            logger.info("AddResponseIDHeader id : "+id);


        } catch (Exception e) {
            try {
                throw new ZuulException(e, INTERNAL_SERVER_ERROR.value(), e.getMessage());
            } catch (ZuulException zuulException) {
                zuulException.printStackTrace();
            }
        }


            System.out.println("post");

            logger.info("Logger>>>>>> PostFilter");
            logger.info(String.format("Logger>>>>>> %s request to %s", request.getMethod(), request.getRequestURL().toString()));
            return null;

    }


}
