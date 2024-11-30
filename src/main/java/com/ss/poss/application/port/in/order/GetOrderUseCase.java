package com.ss.poss.application.port.in.order;

import com.ss.poss.domain.order.model.Order;

import java.util.UUID;

public interface GetOrderUseCase {
    Order getOrderById(UUID id);
}
