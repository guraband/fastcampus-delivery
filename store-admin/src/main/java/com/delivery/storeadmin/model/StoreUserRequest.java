package com.delivery.storeadmin.model;

import com.delivery.db.enums.StoreUserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreUserRequest {
    @NotBlank
    private Long storeId;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private StoreUserRole role;
}
