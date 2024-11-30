package com.ss.poss.application.port.in.orderdetail;

import com.ss.poss.domain.orderdetail.model.OrderDetail;

public interface CreateOrderDetailUseCase {
    OrderDetail createOrderDetail(OrderDetail orderDetail);
}
