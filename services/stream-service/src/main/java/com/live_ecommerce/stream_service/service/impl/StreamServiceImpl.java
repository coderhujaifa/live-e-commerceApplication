package com.live_ecommerce.stream_service.service.impl;


import com.live_ecommerce.stream_service.dto.AnalyticsResponse;
import com.live_ecommerce.stream_service.dto.PinProductRequest;
import com.live_ecommerce.stream_service.dto.StreamCreateRequest;
import com.live_ecommerce.stream_service.dto.StreamResponse;
import com.live_ecommerce.stream_service.entity.StreamProduct;
import com.live_ecommerce.stream_service.entity.StreamStatus;
import com.live_ecommerce.stream_service.repository.StreamProductRepository;
import com.live_ecommerce.stream_service.repository.StreamRepository;
import com.live_ecommerce.stream_service.service.StreamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import com.live_ecommerce.stream_service.entity.Stream;


@Service
@RequiredArgsConstructor
public class StreamServiceImpl implements StreamService {

    private final StreamRepository streamRepository;
    private final StreamProductRepository streamProductRepository;

    @Override
    public StreamResponse createStream(StreamCreateRequest request) {
        Stream stream = Stream.builder()
                .id("stream-" + UUID.randomUUID())
                .title(request.getTitle())
                .description(request.getDescription())
                .scheduledAt(request.getScheduledAt())
                .hostId(request.getHostId())
                .status(StreamStatus.SCHEDULED)
                .createdAt(LocalDateTime.now())
                .build();

        streamRepository.save(stream);

        return toResponse(stream);
    }

    @Override
    public StreamResponse startStream(String id) {
        Stream stream = streamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stream not found"));

        // In real scenario -> Call Agora API here to create stream channel
        stream.setStatus(StreamStatus.LIVE);
        stream.setStartedAt(LocalDateTime.now());
        stream.setStreamKey("AGORA_KEY_" + UUID.randomUUID());
        stream.setPlaybackUrl("https://agora.io/live/" + stream.getId());

        streamRepository.save(stream);
        return toResponse(stream);
    }

    @Override
    public StreamResponse endStream(String id) {
        Stream stream = streamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stream not found"));

        stream.setStatus(StreamStatus.ENDED);
        stream.setEndedAt(LocalDateTime.now());
        stream.setTotalViewers((int) (Math.random() * 500)); // dummy analytics
        stream.setTotalLikes((int) (Math.random() * 2000));
        stream.setTotalOrders((int) (Math.random() * 100));

        streamRepository.save(stream);
        return toResponse(stream);
    }

    @Override
    public List<StreamResponse> getLiveStreams() {
        return streamRepository.findByStatus(StreamStatus.LIVE)
                .stream().map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StreamResponse getStreamById(String id) {
        Stream stream = streamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stream not found"));
        return toResponse(stream);
    }

    @Override
    public StreamProduct pinProduct(String id, PinProductRequest request) {
        Stream stream = streamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stream not found"));

        // ✅ Create StreamProduct object with dynamic values from request
        StreamProduct product = StreamProduct.builder()
                .streamId(stream.getId())
                .productId(request.getProductId())
                .productName(request.getName())   // ✅ works now
                .price(request.getPrice())        // ✅ works now
                .pinnedAt(LocalDateTime.now())
                .build();


        return streamProductRepository.save(product);
    }

    @Override
    public List<StreamProduct> getStreamProducts(String id) {
        return streamProductRepository.findByStreamId(id);
    }

    @Override
    public AnalyticsResponse getAnalytics(String id) {
        Stream stream = streamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stream not found"));

        // Dummy analytics
        return AnalyticsResponse.builder()
                .streamId(stream.getId())
                .totalViewers(stream.getTotalViewers())
                .uniqueViewers(stream.getTotalViewers() - 10)
                .totalLikes(stream.getTotalLikes())
                .totalComments((int) (Math.random() * 500))
                .totalOrders(stream.getTotalOrders())
                .topProductSold(AnalyticsResponse.ProductAnalytics.builder()
                        .productId("prod-201")
                        .name("Winter Jacket")
                        .unitsSold(30)
                        .build())
                .build();
    }

    @Override
    public List<StreamResponse> getStreamsByHost(String hostId) {
        return streamRepository.findByHostId(hostId)
                .stream().map(this::toResponse)
                .collect(Collectors.toList());
    }

    // Utility method
    private StreamResponse toResponse(Stream s) {
        return StreamResponse.builder()
                .id(s.getId())
                .title(s.getTitle())
                .description(s.getDescription())
                .status(s.getStatus().name())
                .hostId(s.getHostId())
                .scheduledAt(s.getScheduledAt())
                .startedAt(s.getStartedAt())
                .endedAt(s.getEndedAt())
                .playbackUrl(s.getPlaybackUrl())
                .build();
    }
}