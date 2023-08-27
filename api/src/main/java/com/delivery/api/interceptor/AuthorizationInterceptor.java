package com.delivery.api.interceptor;

import com.delivery.api.business.TokenBusiness;
import com.delivery.common.exception.ApiException;
import com.delivery.common.status.ErrorStatusCode;
import com.delivery.common.status.TokenErrorStatusCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    private final TokenBusiness tokenBusiness;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Authorization Interceptor url : {}", request.getRequestURI());

        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            return true;
        }

        if (handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        var authorizationHeader = StringUtils.defaultString(request.getHeader("Authorization")).split(" ");
        if (authorizationHeader.length != 2 || !authorizationHeader[0].equals("Bearer") || StringUtils.isEmpty(authorizationHeader[1])) {
            throw new ApiException(TokenErrorStatusCode.TOKEN_NOT_FOUND);
        }
        var accessToken = authorizationHeader[1];

        Long id = tokenBusiness.getIdFromAccessToken(accessToken);
        if (id == null) {
            throw new ApiException(ErrorStatusCode.BAD_REQUEST, "인증 실패");
        }

        var requestContext = Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        requestContext.setAttribute("id", id, RequestAttributes.SCOPE_REQUEST);
        return true;
    }
}
