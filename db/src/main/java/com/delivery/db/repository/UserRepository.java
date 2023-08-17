package com.delivery.db.repository;

import com.delivery.db.entity.User;
import com.delivery.db.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByIdAndStatusOrderByIdDesc(long id, UserStatus status);

    Optional<User> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);
}
