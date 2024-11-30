package com.ss.poss.application.port.in.order;

import com.ss.poss.domain.order.model.Order;

public interface DeleteOrderUseCase {
    void deleteOrder(Order order);
}
