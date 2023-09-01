package com.delivery.storeadmin.component;

public interface SseConnectionPool<K, V> {

    void addSession(V connection);

    V getSession(K uniqueKey);

    void onCompleteCallback(V connection);
}
