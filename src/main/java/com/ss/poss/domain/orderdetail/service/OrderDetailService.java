package com.ss.poss.domain.orderdetail.service;

import com.ss.poss.application.port.in.orderdetail.CreateOrderDetailUseCase;
import com.ss.poss.application.port.in.orderdetail.GetListOrderDetailUseCase;
import com.ss.poss.application.port.in.orderdetail.GetOrderDetailUseCase;
import com.ss.poss.domain.orderdetail.model.OrderDetail;
import com.ss.poss.infrastructure.adapter.out.persistence.orderdetail.OrderDetailPersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
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
        LOG.info("Create order detail: {} started", orderDetail.getOrderDetailId());
        orderDetailPersistenceAdapter.saveOrderDetail(orderDetail);
        LOG.info("Create order detail: {} finished", orderDetail.getOrderDetailId());
        return orderDetail;
    }

    @Override
    public List<OrderDetail> createOrderDetails(List<OrderDetail> orderDetailList) {
        LOG.info("Create order detail list : {} started", orderDetailList.size());
        orderDetailList = orderDetailPersistenceAdapter.saveOrderDetails(orderDetailList);
        LOG.info("Create order detail list : {} finished", orderDetailList.size());

        return orderDetailList;
    }

    @Override
    public List<OrderDetail> getListOrderDetailByOrderId(UUID orderId) {
        return List.of();
    }

    @Override
    public OrderDetail getOrderDetailById(UUID id) {
        return null;
    }
}
