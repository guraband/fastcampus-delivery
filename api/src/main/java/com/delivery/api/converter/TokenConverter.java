package com.delivery.api.converter;

import com.delivery.common.annotation.Converter;
import com.delivery.common.dto.Token;
import com.delivery.common.dto.TokenResponse;
import com.delivery.common.exception.ApiException;
import com.delivery.common.status.ErrorStatusCode;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
@Converter
public class TokenConverter {
    public TokenResponse toResponse(Token accessToken, Token refreshToken) {
        Objects.requireNonNull(accessToken, () -> {
            throw new ApiException(ErrorStatusCode.NULL_POINT);
        });
        Objects.requireNonNull(refreshToken, () -> {
            throw new ApiException(ErrorStatusCode.NULL_POINT);
        });

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
