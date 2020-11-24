package com.direa.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletRequest;

public class RouteFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {

        return RequestContext.getCurrentContext().getRouteHost() != null
                && RequestContext.getCurrentContext().sendZuulResponse();
//        return true;
    }

    @Override
    public Object run() throws ZuulException {
//    public Object run() {
//        OkHttpClient httpClient = new OkHttpClient.Builder()
//                // customize
//                .build();
//
//        RequestContext context = RequestContext.getCurrentContext();
//        HttpServletRequest request = context.getRequest();
//
//        String method = request.getMethod();
//
//        String uri = this.helper.buildZuulRequestURI(request);
//
//        Headers.Builder headers = new Headers.Builder();
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String name = headerNames.nextElement();
//            Enumeration<String> values = request.getHeaders(name);
//
//            while (values.hasMoreElements()) {
//                String value = values.nextElement();
//                headers.add(name, value);
//            }
//        }
//
//        InputStream inputStream = request.getInputStream();
//
//        RequestBody requestBody = null;
//        if (inputStream != null && HttpMethod.permitsRequestBody(method)) {
//            MediaType mediaType = null;
//            if (headers.get("Content-Type") != null) {
//                mediaType = MediaType.parse(headers.get("Content-Type"));
//            }
//            requestBody = RequestBody.create(mediaType, StreamUtils.copyToByteArray(inputStream));
//        }
//
//        Request.Builder builder = new Request.Builder()
//                .headers(headers.build())
//                .url(uri)
//                .method(method, requestBody);
//
//        Response response = httpClient.newCall(builder.build()).execute();
//
//        LinkedMultiValueMap<String, String> responseHeaders = new LinkedMultiValueMap<>();
//
//        for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet()) {
//            responseHeaders.put(entry.getKey(), entry.getValue());
//        }
//
//        this.helper.setResponse(response.code(), response.body().byteStream(),
//                responseHeaders);
//        context.setRouteHost(null); // prevent SimpleHostRoutingFilter from running



        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        System.out.println("route");
        logger.info("Logger>>>>> Route Filter");
        logger.info(String.format("Logger>>>>>> %s request to %s", request.getMethod(), request.getRequestURL().toString()));
        return null;
    }
}

