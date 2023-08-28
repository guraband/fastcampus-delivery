package com.delivery.storeadmin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return storeUserService.getStoreUserByEmail(username)
                .map(it -> User.builder()
                        .username(it.getEmail())
                        .password(it.getPassword())
                        .roles(it.getRole().toString())
                        .build()).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
