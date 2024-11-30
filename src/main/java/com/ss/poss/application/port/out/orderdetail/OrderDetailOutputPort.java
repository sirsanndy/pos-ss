package com.ss.poss.application.port.out.orderdetail;

import com.ss.poss.domain.orderdetail.model.OrderDetail;

import java.util.List;
import java.util.UUID;

public interface OrderDetailOutputPort {
    OrderDetail saveOrderDetail(OrderDetail orderDetail);
    OrderDetail getOrderDetailById(UUID id);
    List<OrderDetail> getOrderDetailByOrderId(UUID orderId);
}
