package com.delivery.storeadmin.controller;

import com.delivery.storeadmin.component.SseConnectionPoolImpl;
import com.delivery.storeadmin.model.StoreUserSession;
import com.delivery.storeadmin.model.StoreUserSseConnection;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sse")
public class SseController {

    private final SseConnectionPoolImpl sseConnectionPool;
    private final ObjectMapper objectMapper;

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect(
            @Parameter(hidden = true)
            @AuthenticationPrincipal StoreUserSession storeUserSession
    ) {
        log.info("login user {}", storeUserSession.getEmail());

        var connection = StoreUserSseConnection.connect(
                storeUserSession.getId().toString(),
                sseConnectionPool,
                objectMapper);
        return connection.getSseEmitter();
    }

    @GetMapping("/push-event")
    public void pushEvent(
            @Parameter(hidden = true)
            @AuthenticationPrincipal StoreUserSession storeUserSession
    ) {
        Optional.ofNullable(sseConnectionPool.getSession(storeUserSession.getId().toString()))
                .ifPresent(it -> {
                    it.sendMessage("Hello~");
                });
    }
}
