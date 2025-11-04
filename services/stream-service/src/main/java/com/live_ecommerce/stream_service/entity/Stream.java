package com.live_ecommerce.stream_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stream {

    @Id
    @Column(length = 100)
    private String id;

    private String title;
    private String description;
    private String hostId;

    @Enumerated(EnumType.STRING)
    private StreamStatus status;

    private LocalDateTime scheduledAt;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    private String streamKey;
    private String playbackUrl;

    private Integer totalViewers;
    private Integer totalLikes;
    private Integer totalOrders;

    private LocalDateTime createdAt;
}
