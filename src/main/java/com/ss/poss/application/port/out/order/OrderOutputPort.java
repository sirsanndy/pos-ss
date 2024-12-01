package com.ss.poss.application.port.out.order;

import com.ss.poss.domain.order.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderOutputPort {
    Order saveOrder(Order order);
    Optional<Order> getOrderById(UUID orderId);
    List<Order> getAllOrders();
}
