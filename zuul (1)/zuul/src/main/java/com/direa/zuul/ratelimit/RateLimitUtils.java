//package com.direa.zuul.ratelimit;
//
//import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
//import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.X_FORWARDED_FOR_HEADER;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Set;
//
//public class RateLimitUtils implements com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils {
//    private static final String ANONYMOUS_USER = "anonymous";
//    private static final String X_FORWARDED_FOR_HEADER_DELIMITER = ",";
//
//    private final RateLimitProperties properties;
//
//    public RateLimitUtils(RateLimitProperties properties) {
//        this.properties = properties;
//    }
//
//
//    public String getUser(final HttpServletRequest request) {
//        return request.getRemoteUser() != null ? request.getRemoteUser() : ANONYMOUS_USER;
//    }
//
//
//    public String getRemoteAddress(final HttpServletRequest request) {
//        String xForwardedFor = request.getHeader(X_FORWARDED_FOR_HEADER);
//        if (properties.isBehindProxy() && xForwardedFor != null) {
//            return xForwardedFor.split(X_FORWARDED_FOR_HEADER_DELIMITER)[0].trim();
//        }
//        return request.getRemoteAddr();
//    }
//
//
//    public Set<String> getUserRoles() {
//        throw new UnsupportedOperationException("Not supported");
//    }
//}
