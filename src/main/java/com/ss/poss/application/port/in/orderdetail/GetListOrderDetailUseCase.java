package com.ss.poss.application.port.in.orderdetail;

import com.ss.poss.domain.orderdetail.model.OrderDetail;

import java.util.List;
import java.util.UUID;

public interface GetListOrderDetailUseCase {
    List<OrderDetail> getListOrderDetailByOrderId(UUID orderId);
}
