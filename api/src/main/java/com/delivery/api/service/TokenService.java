package com.delivery.api.service;

import com.delivery.common.dto.Token;
import com.delivery.common.exception.ApiException;
import com.delivery.common.helper.JwtTokenHelper;
import com.delivery.common.status.ErrorStatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final JwtTokenHelper jwtTokenHelper;

    public Token issueAccessToken(Long id) {
        var data = new HashMap<String, Object>();
        data.put("id", id);
        return jwtTokenHelper.issueAccessToken(data);
    }

    public Token issueRefreshToken(Long id) {
        var data = new HashMap<String, Object>();
        data.put("id", id);
        return jwtTokenHelper.issueRefreshToken(data);
    }

    public Long getIdOrThrow(String token) {
        var map = jwtTokenHelper.validate(token);
        var id = map.get("id");

        Objects.requireNonNull(id, () -> {
            throw new ApiException(ErrorStatusCode.NULL_POINT);
        });

        return Long.parseLong(id.toString());
    }
}
