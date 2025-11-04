package com.live_ecommerce.stream_service.service;

import com.live_ecommerce.stream_service.dto.AnalyticsResponse;
import com.live_ecommerce.stream_service.dto.PinProductRequest;
import com.live_ecommerce.stream_service.dto.StreamCreateRequest;
import com.live_ecommerce.stream_service.dto.StreamResponse;
import com.live_ecommerce.stream_service.entity.StreamProduct;

import java.util.List;

public interface StreamService {
    StreamResponse createStream(StreamCreateRequest request);
    StreamResponse startStream(String id);
    StreamResponse endStream(String id);
    List<StreamResponse> getLiveStreams();
    StreamResponse getStreamById(String id);
    StreamProduct pinProduct(String id, PinProductRequest request);
    List<StreamProduct> getStreamProducts(String id);
    AnalyticsResponse getAnalytics(String id);
    List<StreamResponse> getStreamsByHost(String hostId);
}