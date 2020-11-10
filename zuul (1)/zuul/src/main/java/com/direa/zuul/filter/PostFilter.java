package com.direa.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class PostFilter extends ZuulFilter{
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

        return "====postfilter test====";
    }
}
