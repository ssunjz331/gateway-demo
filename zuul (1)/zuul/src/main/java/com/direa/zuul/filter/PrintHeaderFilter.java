//package com.direa.zuul.filter;
//
//import com.netflix.zuul.context.RequestContext;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Collections;
//import java.util.List;
//
//import static com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.RateLimitConstants.HEADER_REMAINING;
//
//
//@Component
//public class PrintHeaderFilter extends OncePerRequestFilter {
//
//    private Logger logger = LoggerFactory.getLogger(PrintHeaderFilter.class);
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        List<String> headerNames = Collections.list(request.getHeaderNames());
//
////        logger.info("requestUrl:" + request.getRequestURL());
//        for (String headerName : headerNames) {
//            logger.info("requestHeader:" + headerName + ":" + request.getHeader(headerName));
//        }
//        filterChain.doFilter(request, response);
//    }
//}
