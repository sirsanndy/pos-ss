package com.ss.poss.domain.orderdetail.service;

import com.ss.poss.application.port.in.orderdetail.CreateOrderDetailUseCase;
import com.ss.poss.application.port.in.orderdetail.GetListOrderDetailUseCase;
import com.ss.poss.application.port.in.orderdetail.GetOrderDetailUseCase;
import com.ss.poss.domain.order.model.Order;
import com.ss.poss.domain.orderdetail.exception.OrderDetailNotFoundException;
import com.ss.poss.domain.orderdetail.model.OrderDetail;
import com.ss.poss.infrastructure.adapter.out.persistence.orderdetail.OrderDetailPersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderDetailService implements CreateOrderDetailUseCase, GetOrderDetailUseCase, GetListOrderDetailUseCase{
    private static final Logger LOG = LoggerFactory.getLogger(OrderDetailService.class);

    private final OrderDetailPersistenceAdapter orderDetailPersistenceAdapter;

    public OrderDetailService(OrderDetailPersistenceAdapter orderDetailPersistenceAdapter) {
        this.orderDetailPersistenceAdapter = orderDetailPersistenceAdapter;
    }

    @Override
    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        LOG.info("Create order detail: {} started", orderDetail.getOrderId());
        orderDetailPersistenceAdapter.saveOrderDetail(orderDetail);
        LOG.info("Create order detail: {} finished", orderDetail.getOrderId());
        return orderDetail;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public List<OrderDetail> createOrderDetails(Order order) {
        LOG.info("Create order detail list : {} started", order.getListItem().size());
        List<OrderDetail> orderDetailList = orderDetailPersistenceAdapter.saveOrderDetails(order);
        LOG.info("Create order detail list : {} finished",  order.getListItem().size());

        return orderDetailList;
    }

    @Override
    public List<OrderDetail> getListOrderDetailByOrderId(UUID orderId) {
        return List.of();
    }

    @Override
    public OrderDetail getOrderDetailById(UUID id) {
        LOG.info("Get order detail by id: {} started", id);
        OrderDetail orderDetail = orderDetailPersistenceAdapter.getOrderDetailById(id)
                .orElseThrow(() -> new OrderDetailNotFoundException(String.format("Order detail not found %s", id)));
        LOG.info("Get order detail by id: {} finished", id);
        return orderDetail;
    }
}
