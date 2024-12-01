package com.ss.poss.domain.order.service;

import com.ss.poss.application.port.in.order.CreateOrderUseCase;
import com.ss.poss.application.port.in.order.GetOrderUseCase;
import com.ss.poss.application.port.in.order.WebhookOrderUserCase;
import com.ss.poss.domain.order.model.Order;
import com.ss.poss.domain.order.model.OrderWebhook;
import com.ss.poss.infrastructure.adapter.out.persistence.order.OrderPersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService implements CreateOrderUseCase, GetOrderUseCase, WebhookOrderUserCase {
    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    private final OrderPersistenceAdapter orderPersistenceAdapter;

    public OrderService(OrderPersistenceAdapter orderPersistenceAdapter) {
        this.orderPersistenceAdapter = orderPersistenceAdapter;
    }

    @Override
    public Order createOrder(Order order) {
        LOG.info("Submit order service: {} started", order);
        orderPersistenceAdapter.saveOrder(order);
        LOG.info("Submit order service: {} finished", order);
        return order;
    }

    @Override
    public Order getOrderById(UUID id) {
        return null;
    }

    @Override
    public void send(OrderWebhook orderWebhook) {

    }
}
