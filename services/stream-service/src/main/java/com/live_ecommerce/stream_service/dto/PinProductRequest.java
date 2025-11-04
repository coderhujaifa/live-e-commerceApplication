package com.live_ecommerce.stream_service.dto;

import lombok.Data;

@Data
public class PinProductRequest {
    private String productId;
    private String name;
    private Double price;
}
