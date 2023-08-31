package com.delivery.storeadmin.controller;

import com.delivery.storeadmin.model.StoreUserSession;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sse")
public class SseController {

    private static final Map<String, SseEmitter> emitterMap = new ConcurrentHashMap<>();

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter connect(
            @Parameter(hidden = true)
            @AuthenticationPrincipal StoreUserSession storeUserSession
    ) {
        log.info("login user {}", storeUserSession.getEmail());

        var emitter = new SseEmitter();
        emitterMap.put(storeUserSession.getId().toString(), emitter);
        System.out.println(storeUserSession.getId().toString() + " : " + emitterMap);

        emitter.onTimeout(() -> {
            log.info("emitter.onTimeout");
            emitter.complete();
        });

        // 클라이언트와 연결이 종료되었을 때
        emitter.onCompletion(() -> {
            log.info("emitter.onCompletion");
            emitterMap.remove(storeUserSession.getId().toString());
        });

        var event = SseEmitter.event()
                .name("onopen")
                .data("connect");

        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }

        return emitter;
    }

    @GetMapping("/push-event")
    public void pushEvent(
            @Parameter(hidden = true)
            @AuthenticationPrincipal StoreUserSession storeUserSession
    ) {

        var event = SseEmitter.event()
                .data("hi!!!");

        // 기존 유저 emitter 조회
        var emitter = emitterMap.get(storeUserSession.getId().toString());

        System.out.println("# pushEvent");
        System.out.println(emitterMap);
        System.out.println(storeUserSession.getId().toString() + " : " + emitter);

        try {
            emitter.send(event);
        } catch (IOException e) {
            emitter.completeWithError(e);
        }
    }
}
