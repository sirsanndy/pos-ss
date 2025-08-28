package com.ss.poss.infrastructure.adapter.out.persistence.order;

import com.ss.poss.application.port.out.order.OrderOutputPort;
import com.ss.poss.domain.order.exception.OrderNotFoundException;
import com.ss.poss.domain.order.mapper.OrderMapper;
import com.ss.poss.domain.order.model.Order;
import com.ss.poss.domain.order.model.OrderStatus;
import com.ss.poss.domain.orderdetail.model.OrderDetail;
import com.ss.poss.domain.orderdetail.service.OrderDetailService;
import com.ss.poss.infrastructure.adapter.config.Adapter;
import com.ss.poss.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Adapter
public class OrderPersistenceAdapter implements OrderOutputPort {
    private static final Logger LOG = LoggerFactory.getLogger(OrderPersistenceAdapter.class);

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderDetailService orderDetailService;

    public OrderPersistenceAdapter(OrderRepository orderRepository, OrderMapper orderMapper, OrderDetailService orderDetailService) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.orderDetailService = orderDetailService;
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Order saveOrder(Order order) {
        LOG.info("SAVE ORDER IN PERSISTENCE LAYER TO DB STARTED");

        if(!CollectionUtils.isEmpty(order.listItem())){
            OrderEntity orderEntity;
            UUID orderId = order.orderId();
            if(orderId != null) {
                orderEntity = orderRepository.findById(orderId)
                        .orElseThrow(() -> new OrderNotFoundException(String.format("ORDER WITH ORDER ID %s IS NOT FOUND",
                                orderId)));
                orderEntity.setOrderStatus(order.orderStatus());
                orderEntity.setTotalPrice(order.totalPrice());
            } else {
                orderEntity = orderMapper.toEntity(order, order.orderStatus());
            }
            orderRepository.save(orderEntity);
            order = orderMapper.toOrder(orderEntity);
            List<OrderDetail> orderDetailList = orderDetailService.createOrderDetails(order);
            order = new Order(order.orderId(), order.orderStatus(), order.totalPrice(), orderDetailList);
            LOG.info("SAVE ORDER IN PERSISTENCE LAYER TO DB FINISHED");
        } else {
            order = new Order(order.orderId(), OrderStatus.REJECTED, BigDecimal.valueOf(0L), Collections.emptyList());
        }
        return order;
    }

    @Override
    public Optional<Order> getOrderById(UUID orderId) {
        LOG.info("GET ORDER IN PERSISTENCE LAYER FROM DB");
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        if(Objects.nonNull(orderEntity)){
            return Optional.of(orderMapper.toOrder(orderEntity));
        }
        return Optional.empty();
    }

    @Override
    public List<Order> getAllOrders() {
        LOG.info("GET LIST ORDER IN PERSISTENCE LAYER FROM DB");
        return orderRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(orderMapper::toOrder)
                .collect(Collectors.toList());
    }
}
