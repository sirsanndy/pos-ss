package com.ss.poss.infrastructure.adapter.out.persistence.order;

import com.ss.poss.application.port.out.order.OrderOutputPort;
import com.ss.poss.domain.order.mapper.OrderMapper;
import com.ss.poss.domain.order.model.Order;
import com.ss.poss.infrastructure.adapter.config.Adapter;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Adapter
public class OrderPersistenceAdapter implements OrderOutputPort {
    private static final Logger LOG = LoggerFactory.getLogger(OrderPersistenceAdapter.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderPersistenceAdapter(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }


    @Override
    public Order saveOrder(Order order) {
        LOG.info("SAVE ORDER IN PERSISTENCE LAYER TO DB STARTED");
        OrderEntity orderEntity = orderMapper.toEntity(order);
        orderRepository.save(orderEntity);
        order.setId(orderEntity.getOrderId());
        LOG.info("SAVE ORDER IN PERSISTENCE LAYER TO DB FINISHED");
        return order;
    }

    @Override
    public Optional<Order> getOrderById(UUID orderId) {
        LOG.info("GET MENU IN PERSISTENCE LAYER FROM DB");
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId).orElse(null);
        if(Objects.nonNull(orderEntity)){
            return Optional.of(orderMapper.toOrder(orderEntity));
        }
        return Optional.empty();
    }
}
