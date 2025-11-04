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
public class StreamProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String streamId;
    private String productId;
    private String productName;
    private Double price;
    private LocalDateTime pinnedAt;
}
