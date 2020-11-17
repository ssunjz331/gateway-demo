package com.direa.zuul.filter;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.github.bucket4j.ConsumptionProbe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class PostFilter extends ZuulFilter{

    private Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

//        RequestContext context = RequestContext.getCurrentContext();
//        HttpServletResponse servletResponse = context.getResponse();
//        servletResponse.addHeader("Sample", UUID.randomUUID().toString());

//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletResponse response = ctx.getResponse();
//        int statusCode = response.getStatus();
//        System.out.println("statusCode="+statusCode);




        RequestContext context = RequestContext.getCurrentContext();
        try (final InputStream responseDataStream = context.getResponseDataStream()) {

            if(responseDataStream == null) {
                logger.info("BODY: {}", "");
                return null;
            }

            String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
            logger.info("BODY: {}", responseData);

            context.setResponseBody(responseData);
        }
        catch (Exception e) {
            throw new ZuulException(e, INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }


//        ConsumptionProbe probe
        




        System.out.println("post");


        return null;

    }
}
