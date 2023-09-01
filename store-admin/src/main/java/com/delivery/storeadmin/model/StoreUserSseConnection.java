package com.delivery.storeadmin.model;

import com.delivery.storeadmin.component.SseConnectionPool;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Getter
@NoArgsConstructor
public class StoreUserSseConnection {
    private StoreUserSseConnection(String uniqueKey,
                                   SseConnectionPool<String, StoreUserSseConnection> sseConnectionPool,
                                   ObjectMapper objectMapper) {
        this.uniqueKey = uniqueKey;
        this.sseConnectionPool = sseConnectionPool;
        this.objectMapper = objectMapper;

        this.sseEmitter = new SseEmitter(60 * 1000L);

        this.sseEmitter.onTimeout(() -> {
            this.sseEmitter.complete();
        });

        // 클라이언트와 연결이 종료되었을 때
        this.sseEmitter.onCompletion(() -> {
            sseConnectionPool.onCompleteCallback(this);
        });

        sseConnectionPool.addSession(this);

        sendMessage("onopen", "connect");
    }

    public static StoreUserSseConnection connect(String uniqueKey,
                                                 SseConnectionPool<String, StoreUserSseConnection> sseConnectionPool,
                                                 ObjectMapper objectMapper) {
        return new StoreUserSseConnection(uniqueKey, sseConnectionPool, objectMapper);
    }

    private String uniqueKey;

    private SseEmitter sseEmitter;

    private SseConnectionPool<String, StoreUserSseConnection> sseConnectionPool;

    private ObjectMapper objectMapper;

    public void sendMessage(String eventName, Object data) {
        try {
            var event = SseEmitter.event()
                    .name(eventName)
                    .data(objectMapper.writeValueAsString(data));

            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }

    public void sendMessage(Object data) {
        try {
            var event = SseEmitter.event()
                    .data(objectMapper.writeValueAsString(data));

            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }
}
