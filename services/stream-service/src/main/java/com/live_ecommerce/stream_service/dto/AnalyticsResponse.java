package com.live_ecommerce.stream_service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticsResponse {

    private String streamId;
    private int totalViewers;
    private int uniqueViewers;
    private int totalLikes;
    private int totalComments;
    private int totalOrders;
    private ProductAnalytics topProductSold;

    // ✅ inner class must be inside the same file, within closing brace of outer class
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductAnalytics {
        private String productId;
        private String name;
        private int unitsSold;
    }
}
