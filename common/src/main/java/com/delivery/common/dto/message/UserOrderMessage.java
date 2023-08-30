package com.delivery.common.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderMessage {
    private Long userOrderId;

    @Override
    public String toString() {
        return "UserOrderMessage{" +
                "userOrderId=" + userOrderId +
                '}';
    }
}
