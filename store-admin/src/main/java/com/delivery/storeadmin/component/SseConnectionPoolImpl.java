package com.delivery.storeadmin.component;

import com.delivery.storeadmin.model.StoreUserSseConnection;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SseConnectionPoolImpl implements SseConnectionPool<String, StoreUserSseConnection> {
    private static final Map<String, StoreUserSseConnection> connectionPool = new ConcurrentHashMap<>();

    @Override
    public void addSession(StoreUserSseConnection connection) {
        connectionPool.put(connection.getUniqueKey(), connection);
    }

    @Override
    public StoreUserSseConnection getSession(String uniqueKey) {
        return connectionPool.get(uniqueKey);
    }

    @Override
    public void onCompleteCallback(StoreUserSseConnection connection) {
        connectionPool.remove(connection.getUniqueKey());
    }
}
