package com.delivery.storeadmin.service;

import com.delivery.storeadmin.model.StoreUserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService implements UserDetailsService {

    private final StoreUserService storeUserService;
    private final StoreService storeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return storeUserService.getStoreUserByEmail(username)
                .map(it -> {
                    var store = storeService.getOrThrow(it.getStoreId());

                    var storeUserSession = StoreUserSession.builder()
                            .id(it.getId())
                            .email(it.getEmail())
                            .password(it.getPassword())
                            .status(it.getStatus())
                            .role(it.getRole())
                            .storeId(store.getId())
                            .storeName(store.getName())
                            .build();
                    return storeUserSession;
                }).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
