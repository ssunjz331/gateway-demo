package com.direa.zuul.filter;


import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.Rate;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitKeyGenerator;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.*;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.Map;

import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.ResponseHeadersVerbosity.NONE;
import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.ResponseHeadersVerbosity.VERBOSE;
import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.*;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class PostFilter2 extends AbstractRateLimitFilter {

    private Logger logger = LoggerFactory.getLogger(PostFilter2.class);
    private RestTemplate restTemplate = new RestTemplate();

    public PostFilter2(RateLimitProperties properties, RouteLocator routeLocator, UrlPathHelper urlPathHelper, RateLimitUtils rateLimitUtils) {
        super(properties, routeLocator, urlPathHelper, rateLimitUtils);
    }

    public PostFilter2(RateLimitProperties rateLimitProperties, RouteLocator routeLocator, UrlPathHelper urlPathHelper, RateLimiter rateLimiter, RateLimitKeyGenerator rateLimitKeyGenerator, RateLimitUtils rateLimitUtils) {
        super(rateLimitProperties, routeLocator, urlPathHelper, rateLimitUtils);
    }


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

            if (responseDataStream == null) {
                logger.info("BODY: {}", "");
                return null;
            }

            String responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
            logger.info("BODY: {}", responseData);

            context.setResponseBody(responseData);
        } catch (Exception e) {
            throw new ZuulException(e, INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }


//        HttpServletRequest request = context.getRequest();
//        HttpServletResponse response = context.getResponse();

        ////여기
//        RateLimitProperties.Policy policy = new RateLimitProperties.Policy();
//        final Long limit = policy.getLimit();
//        Rate rate = new Rate();
//        final Long remaining = rate.getRemaining();
//
//
//        String apiKey = request.getHeader("X-api-key");
//        String kk = response.getHeader("X-RateLimit-Limit");
//        logger.info("logger>> api-key는 "+apiKey);
//        logger.info("logger>> kk는 "+kk);
//
//
//        ResponseEntity<String> rp = restTemplate.getForEntity("http://localhost:8765/service-a/advanced", String.class);
//        String kk2 = rp.getHeaders(X-RateLimit-Limit);


        //////여기
        final RateLimiter rateLimiter = null;
        final RateLimitKeyGenerator rateLimitKeyGenerator = null;
        final ApplicationEventPublisher eventPublisher;

        final RequestContext ctx = RequestContext.getCurrentContext();
        final HttpServletResponse response = ctx.getResponse();
        final HttpServletRequest request = ctx.getRequest();
        final Route route = (Route) request;

        for (RateLimitProperties.Policy policy : policy(route, request)) {
            Map<String, String> responseHeaders = Maps.newHashMap();

            final String key = rateLimitKeyGenerator.key(request, route, policy);
            final Rate rate = rateLimiter.consume(policy, key, null);

            final Long limit = policy.getLimit();
            final Long remaining = rate.getRemaining();
            if (limit != null) {
                responseHeaders.put(HEADER_LIMIT, String.valueOf(limit));
                responseHeaders.put(HEADER_REMAINING, String.valueOf(Math.max(remaining, 0)));
            }

            final Duration quota = policy.getQuota();
            final Long remainingQuota = rate.getRemainingQuota();
            if (quota != null) {
                request.setAttribute(REQUEST_START_TIME, System.currentTimeMillis());
                responseHeaders.put(HEADER_QUOTA, String.valueOf(quota.getSeconds()));
                responseHeaders.put(HEADER_REMAINING_QUOTA, String.valueOf(MILLISECONDS.toSeconds(Math.max(remainingQuota, 0))));
            }

            responseHeaders.put(HEADER_RESET, String.valueOf(rate.getReset()));

            if (!NONE.equals(properties.getResponseHeaders())) {
                final String httpHeaderKey = key.replaceAll("[^A-Za-z0-9-.]", "_").replaceAll("__", "_");
                for (Map.Entry<String, String> headersEntry : responseHeaders.entrySet()) {
                    String header = VERBOSE.equals(properties.getResponseHeaders()) ? headersEntry.getKey() + "-" + httpHeaderKey : headersEntry.getKey();
                    response.setHeader(header, headersEntry.getValue());
                }
            }
        }

            System.out.println("post");


            return null;



    }
}
