package com.live_ecommerce.stream_service.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessage {
    private String type; // CHAT, LIKE, PRODUCT_PIN
    private String userId;
    private String username;
    private String message;
    private LocalDateTime timestamp;
}
