package com.ss.poss.domain.order.service;

import com.ss.poss.application.port.in.order.CreateOrderUseCase;
import com.ss.poss.application.port.in.order.GetOrderUseCase;
import com.ss.poss.domain.order.model.Order;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService implements CreateOrderUseCase, GetOrderUseCase {
    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public Order getOrderById(UUID id) {
        return null;
    }
}
