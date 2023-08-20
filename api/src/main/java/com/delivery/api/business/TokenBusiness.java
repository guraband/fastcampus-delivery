package com.delivery.api.business;

import com.delivery.api.converter.TokenConverter;
import com.delivery.api.service.TokenService;
import com.delivery.common.annotation.Business;
import com.delivery.common.dto.TokenResponse;
import com.delivery.common.exception.ApiException;
import com.delivery.common.status.ErrorStatusCode;
import com.delivery.db.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class TokenBusiness {

    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    public TokenResponse issueToken(User userEntity) {
        return Optional.ofNullable(userEntity)
                .map(item -> {
                    var id = item.getId();
                    var accessToken = tokenService.issueAccessToken(id);
                    var refreshToken = tokenService.issueRefreshToken(id);
                    return tokenConverter.toResponse(accessToken, refreshToken);
                })
                .orElseThrow(() -> new ApiException(ErrorStatusCode.NULL_POINT));
    }

    public Long getIdFromAccessToken(String accessToken) {
        return tokenService.getIdOrThrow(accessToken);
    }
}
