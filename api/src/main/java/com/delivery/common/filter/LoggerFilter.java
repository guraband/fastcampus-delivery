package com.delivery.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Slf4j
@Component
public class LoggerFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        var req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        var res = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, res);

        var reqHeaders = new StringBuilder();
        req.getHeaderNames().asIterator().forEachRemaining(name -> {
            reqHeaders.append(name).append(" : ").append(req.getHeader(name)).append(", ");
        });

        var reqBody = new String(req.getContentAsByteArray());

        log.info("# [{}] {} / {}", req.getMethod(), req.getRequestURI());
        log.info(">>> header : {}, body : {}", reqHeaders, reqBody);

        var resHeaders = new StringBuilder();
        res.getHeaderNames().forEach(name -> {
            resHeaders.append(name).append(" : ").append(res.getHeader(name)).append(", ");
        });
        var resBody = new String(res.getContentAsByteArray());
        log.info("<<< header : {}, body : {}", resHeaders, resBody);

        res.copyBodyToResponse();
    }
}
