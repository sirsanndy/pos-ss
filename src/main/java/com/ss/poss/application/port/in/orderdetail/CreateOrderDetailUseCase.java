package com.ss.poss.application.port.in.orderdetail;

import com.ss.poss.domain.order.model.Order;
import com.ss.poss.domain.orderdetail.model.OrderDetail;

import java.util.List;

public interface CreateOrderDetailUseCase {
    OrderDetail createOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> createOrderDetails(Order order);
}
