package com.live_ecommerce.stream_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class StreamResponse {
    private String id;
    private String title;
    private String description;
    private String status;
    private String playbackUrl;
    private String hostId;
    private LocalDateTime scheduledAt;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
