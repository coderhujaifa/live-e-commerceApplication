package com.live_ecommerce.stream_service.controller;

import com.live_ecommerce.stream_service.dto.AnalyticsResponse;
import com.live_ecommerce.stream_service.dto.PinProductRequest;
import com.live_ecommerce.stream_service.dto.StreamCreateRequest;
import com.live_ecommerce.stream_service.dto.StreamResponse;
import com.live_ecommerce.stream_service.entity.StreamProduct;
import com.live_ecommerce.stream_service.service.StreamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/streams")
@RequiredArgsConstructor
public class StreamController {

    private final StreamService streamService;

    @PostMapping
    public ResponseEntity<StreamResponse> createStream(@RequestBody @Valid StreamCreateRequest request) {
        return ResponseEntity.ok(streamService.createStream(request));
    }

    @PostMapping("/{id}/start")
    public ResponseEntity<StreamResponse> startStream(@PathVariable String id) {
        return ResponseEntity.ok(streamService.startStream(id));
    }

    @PostMapping("/{id}/end")
    public ResponseEntity<StreamResponse> endStream(@PathVariable String id) {
        return ResponseEntity.ok(streamService.endStream(id));
    }

    @GetMapping("/live")
    public ResponseEntity<List<StreamResponse>> getLiveStreams() {
        return ResponseEntity.ok(streamService.getLiveStreams());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StreamResponse> getStreamDetails(@PathVariable String id) {
        return ResponseEntity.ok(streamService.getStreamById(id));
    }

    @PostMapping("/{id}/products")
    public ResponseEntity<StreamProduct> pinProduct(@PathVariable String id, @RequestBody PinProductRequest request) {
        return ResponseEntity.ok(streamService.pinProduct(id, request));
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<StreamProduct>> getStreamProducts(@PathVariable String id) {
        return ResponseEntity.ok(streamService.getStreamProducts(id));
    }

    @GetMapping("/{id}/analytics")
    public ResponseEntity<AnalyticsResponse> getAnalytics(@PathVariable String id) {
        return ResponseEntity.ok(streamService.getAnalytics(id));
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<StreamResponse>> getStreamsByHost(@PathVariable String hostId) {
        return ResponseEntity.ok(streamService.getStreamsByHost(hostId));
    }
}
