package com.ss.poss.infrastructure.adapter.out.persistence.orderdetail;

import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, UUID> {
}
