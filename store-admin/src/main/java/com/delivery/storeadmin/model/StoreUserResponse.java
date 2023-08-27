package com.delivery.storeadmin.model;

import com.delivery.db.enums.StoreUserRole;
import com.delivery.db.enums.StoreUserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreUserResponse {

    private UserResponse user;

    private StoreResponse store;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class UserResponse {
        private String id;

        private String email;

        private StoreUserStatus status;

        private StoreUserRole role;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class StoreResponse {
        private Long id;

        private String name;
    }
}
