package com.ss.poss.application.port.in.order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ss.poss.domain.order.model.Order;

import java.io.IOException;

public interface CreateOrderUseCase {
    Order createOrder(Order order) throws IOException;
}
