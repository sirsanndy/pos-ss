package com.ss.poss.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ORDER_DETAIL")
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

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getMenuId() {
        return menuId;
    }

    public void setMenuId(UUID menuId) {
        this.menuId = menuId;
    }

    public MenuEntity getMenu() {
        return menu;
    }

    public void setMenu(MenuEntity menu) {
        this.menu = menu;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
