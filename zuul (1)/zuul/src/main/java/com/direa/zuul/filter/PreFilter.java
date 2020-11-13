package com.direa.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.FORWARD_TO_KEY;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVICE_ID_KEY;

public class PreFilter extends ZuulFilter {
    private static Logger logger =  LoggerFactory.getLogger(PreFilter.class);


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {

//        RequestContext ctx = RequestContext.getCurrentContext();
//        return !ctx.containsKey(FORWARD_TO_KEY) // a filter has already forwarded
//                && !ctx.containsKey(SERVICE_ID_KEY); // a filter has already determined serviceId

        return true;

    }

    @Override
    public Object run() throws ZuulException {

//        final RequestContext ctx = RequestContext.getCurrentContext();
//        final HttpServletResponse response = ctx.getResponse();
//        final HttpServletRequest request = ctx.getRequest();
//        final Route route = (Route) request;
//
//        policy(route, request).forEach(policy -> {
//            Map<String, String> responseHeaders = Maps.newHashMap();
//
//            final String key = rateLimitKeyGenerator.key(request, route, policy);
//            final Rate rate = rateLimiter.consume(policy, key, null);
//
//            final Long limit = policy.getLimit();
//            final Long remaining = rate.getRemaining();
//            if (limit != null) {
//                responseHeaders.put(HEADER_LIMIT, String.valueOf(limit));
//                responseHeaders.put(HEADER_REMAINING, String.valueOf(Math.max(remaining, 0)));
//            }
//
//            final Duration quota = policy.getQuota();
//            final Long remainingQuota = rate.getRemainingQuota();
//            if (quota != null) {
//                request.setAttribute(REQUEST_START_TIME, System.currentTimeMillis());
//                responseHeaders.put(HEADER_QUOTA, String.valueOf(quota.getSeconds()));
//                responseHeaders.put(HEADER_REMAINING_QUOTA, String.valueOf(MILLISECONDS.toSeconds(Math.max(remainingQuota, 0))));
//            }
//
//            responseHeaders.put(HEADER_RESET, String.valueOf(rate.getReset()));
//
//            if (!NONE.equals(properties.getResponseHeaders())) {
//                final String httpHeaderKey = key.replaceAll("[^A-Za-z0-9-.]", "_").replaceAll("__", "_");
//                for (Map.Entry<String, String> headersEntry : responseHeaders.entrySet()) {
//                    String header = VERBOSE.equals(properties.getResponseHeaders()) ? headersEntry.getKey() + "-" + httpHeaderKey : headersEntry.getKey();
//                    response.setHeader(header, headersEntry.getValue());
//                }
//            }
//
//            if ((limit != null && remaining < 0) || (quota != null && remainingQuota < 0)) {
//                ctx.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
//                ctx.put(RATE_LIMIT_EXCEEDED, "true");
//                ctx.setSendZuulResponse(false);
//
//                eventPublisher.publishEvent(new RateLimitExceededEvent(this, policy, rateLimitUtils.getRemoteAddress(request)));
//
//                throw new RateLimitExceededException();
//            }
//        });

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        System.out.println("pre");

        return null;

    }
}
