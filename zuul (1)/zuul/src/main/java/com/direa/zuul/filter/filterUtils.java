package com.direa.zuul.filter;

import com.netflix.zuul.context.RequestContext;

public class filterUtils {

    public String getServiceId(){
        RequestContext ctx = RequestContext.getCurrentContext();

        //We might not have a service id if we are using a static, non-eureka route.
        if (ctx.get("serviceId")==null) return "";
        return ctx.get("serviceId").toString();
    }

}
