package com.ss.poss.infrastructure.adapter.out.persistence.order;

import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    Optional<OrderEntity> findById(UUID orderId);

    List<OrderEntity> findAllByOrderByCreatedAtDesc();
}
