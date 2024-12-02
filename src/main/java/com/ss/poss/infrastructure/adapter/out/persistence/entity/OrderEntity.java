package com.ss.poss.infrastructure.adapter.out.persistence.entity;

import com.ss.poss.domain.order.model.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMER_ORDER")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "pg-uuid"
    )
    @Column(name = "ORDER_ID")
    private UUID orderId;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;

    @Column(name = "ORDER_STATUS")
    @Enumerated()
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.REMOVE)
    @PrimaryKeyJoinColumn
    private List<OrderDetailEntity> listItem;
}
