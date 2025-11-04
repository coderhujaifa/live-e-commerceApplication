package com.live_ecommerce.stream_service.repository;

import com.live_ecommerce.stream_service.entity.StreamStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.live_ecommerce.stream_service.entity.Stream;


@Repository
public interface StreamRepository extends JpaRepository<Stream, String> {
    List<Stream> findByStatus(StreamStatus status);
    List<Stream> findByHostId(String hostId);
}