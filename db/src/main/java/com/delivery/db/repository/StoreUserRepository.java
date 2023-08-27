package com.delivery.db.repository;

import com.delivery.db.entity.StoreUser;
import com.delivery.db.enums.StoreUserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreUserRepository extends JpaRepository<StoreUser, Long> {
    Optional<StoreUser> findFirstByEmailAndStatusOrderByIdDesc(String email, StoreUserStatus status);
}
