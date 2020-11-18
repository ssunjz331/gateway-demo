//package com.direa.zuul.filter;
//
//import com.google.common.collect.Maps;
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.Rate;
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitKeyGenerator;
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils;
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimiter;
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.filters.RateLimitPreFilter;
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitExceededEvent;
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitExceededException;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.cloud.netflix.zuul.filters.Route;
//import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.util.UrlPathHelper;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.time.Duration;
//import java.util.Map;
//
//import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.ResponseHeadersVerbosity.NONE;
//import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.ResponseHeadersVerbosity.VERBOSE;
//import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.*;
//import static java.util.concurrent.TimeUnit.MILLISECONDS;
//
//public class PreFilter2 extends RateLimitPreFilter {
//    public PreFilter2(RateLimitProperties properties, RouteLocator routeLocator, UrlPathHelper urlPathHelper, RateLimiter rateLimiter, RateLimitKeyGenerator rateLimitKeyGenerator, RateLimitUtils rateLimitUtils, ApplicationEventPublisher eventPublisher) {
//        super(properties, routeLocator, urlPathHelper, rateLimiter, rateLimitKeyGenerator, rateLimitUtils, eventPublisher);
//    }
//
//
//    private static Logger logger = LoggerFactory.getLogger(PreFilter.class);
////    private RestTemplate restTemplate = new RestTemplate();
//
//
//
//    @Override
//    public Object run() {
//        final RequestContext ctx = RequestContext.getCurrentContext();
//        final HttpServletResponse response = ctx.getResponse();
//        final HttpServletRequest request = ctx.getRequest();
//        final Route route = (Route) request;
//
//        for (RateLimitProperties.Policy policy : policy(route, request)) {
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
//        }
//
//        return null;
//    }
//}
