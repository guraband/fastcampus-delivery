package com.delivery.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLoginRequest {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
