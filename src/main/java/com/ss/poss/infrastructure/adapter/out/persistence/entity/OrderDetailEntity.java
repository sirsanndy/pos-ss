package com.ss.poss.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "ORDER_DETAIL")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(OrderDetailId.class)
public class OrderDetailEntity extends BaseEntity {
    @Id
    @Column(name = "ORDER_ID", nullable = false)
    private UUID orderId;

    @Id
    @Column(name = "MENU_ID", nullable = false)
    private UUID menuId;

    @ManyToOne
    @MapsId("menuId")
    @JoinColumn(name = "MENU_ID")
    private MenuEntity menu;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "ORDER_ID")
    private OrderEntity order;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Column(name = "TOTAL_PRICE")
    private BigDecimal totalPrice;
}
