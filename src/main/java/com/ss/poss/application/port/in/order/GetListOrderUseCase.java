package com.ss.poss.application.port.in.order;

import com.ss.poss.domain.order.model.Order;

import java.util.List;

public interface GetListOrderUseCase {
    List<Order> getAllOrder();
}
