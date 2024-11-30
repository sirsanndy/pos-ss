package com.ss.poss.application.port.in.orderdetail;

import com.ss.poss.domain.orderdetail.model.OrderDetail;

import java.util.UUID;

public interface GetOrderDetailUseCase {
    OrderDetail getOrderDetailById(UUID id);
}
