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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

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
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = {Exception.class, RuntimeException.class})
    public Order saveOrder(Order order) {
        LOG.info("SAVE ORDER IN PERSISTENCE LAYER TO DB STARTED");

        if(!CollectionUtils.isEmpty(order.getListItem())){
            OrderEntity orderEntity;
            if(order.getOrderId() != null) {
                orderEntity = orderRepository.findByOrderId(order.getOrderId())
                        .orElseThrow(() -> new OrderNotFoundException(String.format("ORDER WITH ORDER ID %s IS NOT FOUND",
                                order.getOrderId().toString())));
            } else {
                orderEntity = orderMapper.toEntity(order);
            }
            orderRepository.save(orderEntity);
            List<OrderDetail> orderDetailList = orderDetailService.createOrderDetails(order.getListItem());
            order.setOrderId(orderEntity.getOrderId());
            order.setListItem(orderDetailList);
            LOG.info("SAVE ORDER IN PERSISTENCE LAYER TO DB FINISHED");
        } else {
            order.setOrderStatus(OrderStatus.REJECTED);
        }
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
