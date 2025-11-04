package com.live_ecommerce.stream_service.websocket;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    @MessageMapping("/streams/{streamId}/chat")
    @SendTo("/topic/streams/{streamId}")
    public ChatMessage sendMessage(@DestinationVariable String streamId, ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());
        return message;
    }
}
