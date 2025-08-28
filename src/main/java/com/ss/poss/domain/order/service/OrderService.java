package com.ss.poss.domain.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.poss.application.port.in.order.*;
import com.ss.poss.domain.order.exception.OrderNotFoundException;
import com.ss.poss.domain.order.model.Order;
import com.ss.poss.domain.order.model.OrderWebhook;
import com.ss.poss.infrastructure.adapter.config.OrderWebSocketHandler;
import com.ss.poss.infrastructure.adapter.out.persistence.order.OrderPersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService implements CreateOrderUseCase, GetOrderUseCase, WebhookOrderUserCase,
        GetListOrderUseCase, DeleteOrderUseCase {
    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    private final OrderPersistenceAdapter orderPersistenceAdapter;
    private final OrderWebSocketHandler webSocketHandler;
    private final ObjectMapper objectMapper;

    public OrderService(OrderPersistenceAdapter orderPersistenceAdapter, OrderWebSocketHandler webSocketHandler, ObjectMapper objectMapper) {
        this.orderPersistenceAdapter = orderPersistenceAdapter;
        this.webSocketHandler = webSocketHandler;
        this.objectMapper = objectMapper;
    }

    @Override
    public Order createOrder(Order order) throws IOException {
        LOG.info("Submit order service: {} started", order.orderId());
        order = orderPersistenceAdapter.saveOrder(order);
        webSocketHandler.broadcastOrder(objectMapper.writeValueAsString(order));
        LOG.info("Submit order service: {} finished", order.orderId());
        return order;
    }

    @Override
    public Order getOrderById(UUID id) {
        LOG.info("Get order by id service: {} started", id);
        Order order = orderPersistenceAdapter.getOrderById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " not found"));
        LOG.info("Get order by id service: {} finished", id);
        return order;
    }

    @Override
    public void send(OrderWebhook orderWebhook) {

    }

    @Override
    public void deleteOrder(Order order) {
        LOG.info("Delete order service: {} started", order.orderId());
    }

    @Override
    public List<Order> getAllOrder() {
        LOG.info("Get list order service");
        List<Order> orderList = orderPersistenceAdapter.getAllOrders();
        return orderList;
    }
}
