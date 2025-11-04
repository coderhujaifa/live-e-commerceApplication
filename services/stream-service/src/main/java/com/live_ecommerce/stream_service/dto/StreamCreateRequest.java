package com.live_ecommerce.stream_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StreamCreateRequest {
    private String title;
    private String description;
    private LocalDateTime scheduledAt;
    private String hostId;
}
