package com.delivery.api.business;

import com.delivery.api.converter.UserConverter;
import com.delivery.api.model.UserLoginRequest;
import com.delivery.api.model.UserRequest;
import com.delivery.api.model.UserResponse;
import com.delivery.api.service.UserService;
import com.delivery.common.annotation.Business;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {
    private final UserConverter userConverter;
    private final UserService userService;

    public UserResponse register(UserRequest request) {
        var entity = userConverter.toEntity(request);
        var newEntity = userService.register(entity);
        return userConverter.toResponse(newEntity);
    }

    public UserResponse login(UserLoginRequest request) {
        var userEntity = userService.login(request.getEmail(), request.getPassword());

        // TODO 토큰 생성

        return userConverter.toResponse(userEntity);
    }
}
