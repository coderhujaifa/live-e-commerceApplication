package com.live_ecommerce.stream_service.repository;

import com.live_ecommerce.stream_service.entity.StreamProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StreamProductRepository extends JpaRepository<StreamProduct, Long> {
    List<StreamProduct> findByStreamId(String streamId);
}
