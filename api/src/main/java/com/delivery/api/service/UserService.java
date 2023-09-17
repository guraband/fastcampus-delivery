package com.delivery.api.service;

import com.delivery.common.exception.ApiException;
import com.delivery.common.status.ErrorStatusCode;
import com.delivery.db.entity.User;
import com.delivery.db.enums.UserStatus;
import com.delivery.db.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User register(User entity) {
        return Optional.ofNullable(entity)
                .map(userRepository::save)
                .orElseThrow(() -> new ApiException(ErrorStatusCode.NULL_POINT, "User Entity is null"));
    }

    private User getUserOrThrow(String email, String password) {
        return Optional.ofNullable(userRepository.findFirstByEmailAndPasswordAndStatusOrderByIdDesc(email, password, UserStatus.REGISTERED)
        ).orElseThrow(() -> new ApiException(ErrorStatusCode.USER_NOT_FOUND));
    }

    public User getUserOrThrow(Long id) {
        return Optional.ofNullable(userRepository.findFirstByIdAndStatusOrderByIdDesc(id, UserStatus.REGISTERED)
        ).orElseThrow(() -> new ApiException(ErrorStatusCode.USER_NOT_FOUND));
    }

    public User login(String email, String password) {
        return getUserOrThrow(email, password);
    }
}
